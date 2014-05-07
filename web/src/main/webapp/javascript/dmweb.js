/*
 * 
 * O conteudo deste arquivo deve ser compactado com YUI compressor
 * 
 * No linux:
 * yui-compressor dmweb.js -o dmweb-min.js --charset utf-8
 * 
 */
//
dojo.require("dijit.registry");
dojo.require("dijit.dijit");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
// uncomment if want dojo widget style checkbox
// dojo.require('dijit.form.CheckBox');
dojo.require("esri.map");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dojo.fx");// needed if use jsapi 3.0
dojo.require("agsjs.dijit.TOC");
dojo.require("dojo.data.ItemFileReadStore");

dojo.require("esri.dijit.BasemapGallery");
dojo.require("esri.arcgis.utils");
dojo.require("dijit.form.Button");
dojo.require("esri.dijit.Scalebar");

dojo.require("esri.dijit.Popup");
dojo.require("esri.tasks.locator");
dojo.require("esri.layers.graphics");

dojo.require("esri.tasks.identify");
dojo.require("dijit.layout.TabContainer");

var identifyTask, identifyParams;
var map, selectionLayer;
var items;

var MOSAICO_DEP       = mosaicoDep();
var CAMADA_DMAE      = camadaDmae();
var MOSAICO_1_1000    = mosaicoAlinhamento();  // alinhamentos
var CAMADA_QUARTEIRAO = camadaQuarteirao();
var CAMADAS;
var REST;
var MAPA_5000;

CAMADAS = urlBaseMapServer(); // Busca url do mapa base no arquivo
								// dmweb-service.xml de acordo com o servidor (
								// produção, desenvolvimento ou homologação )

MAPA_5000 = urlBaseMap5000(); // Busca url do mapa base 5000 no arquivo
								// dmweb-service.xml de acordo com o servidor (
								// produção, desenvolvimento ou homologação )

function showMenu() {
	// Abre o painel Declaração municipal
	dijit.byId("accordionId").selectChild(dijit.byId("declaracao"));

}

function executeTask(openModalPanel) {

	var queryTask = new esri.tasks.QueryTask(CAMADAS + "/" + CAMADA_QUARTEIRAO);
	var query = new esri.tasks.Query();

	query.returnGeometry = true;
	query.outFields = [ "QUARTEIRAO", "CODIGO", "OBJECTID" ];
	query.where = " CODIGO = " + ueu_ + " and QUARTEIRAO = " + qrt_;

	dojo.connect(queryTask, "onComplete", function(featureSet) {
		// build an array of attributes
		items = dojo.map(featureSet.features, function(feature) {
			return feature.attributes;
		});

		var data = {
			identifier : "OBJECTID",
			items : items
		};
		store = new dojo.data.ItemFileReadStore({
			data : data
		});

		dojo.forEach(featureSet.features, function(feature) {
			map.graphics.add(feature);
		});

		zoomRow(data.items[0].OBJECTID);
		
	});

	queryTask.execute(query);

	if (openModalPanel) {
		showModalPanel();
//		executeTask(false);
	}

}

function zoomRow(id) {
	selectionLayer.clear();
	dojo.some(map.graphics.graphics, function(graphic) {
		if (graphic.attributes) {
			if (graphic.attributes.OBJECTID.toString() == id) {
				var selectedState = new esri.Graphic(graphic.geometry)
						.setAttributes(graphic.attributes);
				selectionLayer.add(selectedState);
				// Zoom to the extent of the parcel - expand it a bit so we
				// aren't zoomed in too close.
				var stateExtent = selectedState.geometry.getExtent()
						.expand(8.0);
				map.setExtent(stateExtent);
				return true;
			}
		}
	});
}

function init() {

	// fillSymbol: new
	// esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID,
	// new
	// esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
	// new dojo.Color([255,0,0]), 2), new dojo.Color([255,255,0,0.25]))

	esri.bundle.widgets.popup.NLS_zoomTo = "Centralizar quadrante selecionado";

	var initialExtent = new esri.geometry.Extent({
		xmin : -5710685.3147830395,
		ymin : -3538136.903834779,
		xmax : -5677294.030456953,
		ymax : -3491728.8031903733,
		"spatialReference" : {
			"wkid" : 102100
		}
	});

	map = new esri.Map("map", {
		extent : initialExtent
	});

	var basemap = new esri.layers.ArcGISTiledMapServiceLayer(
			"http://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");

	map.addLayer(basemap);

	var planodir = new esri.layers.ArcGISDynamicMapServiceLayer(CAMADAS, {
		id : 'planodir',
		opacity : 0.8
	});

	
//	var planodir = new esri.layers.ArcGISDynamicMapServiceLayer(CAMADAS + "/0", {
//		id : 'planodir',
//		opacity : 0.8
//	});
//
//	var infra = new esri.layers.ArcGISDynamicMapServiceLayer(CAMADAS + "/10", {
//		id : 'infra',
//		opacity : 0.8
//	});
//
//	var condicionantes = new esri.layers.ArcGISDynamicMapServiceLayer(CAMADAS + "/18", {
//		id : 'condicionantes',
//		opacity : 0.8
//	});
//
//	var ambiente = new esri.layers.ArcGISDynamicMapServiceLayer(CAMADAS + "/25", {
//		id : 'ambiente',
//		opacity : 0.8
//	});
	
	
	
	dojo.connect(map, 'onLayersAddResult', function(results) {
		var toc = new agsjs.dijit.TOC({
			map : map,
			layerInfos : [ {
				layer : planodir,
				title : "Camadas de Informação"
			}, 			
]
		}, 'tocDiv');
		toc.startup();
		

	
//	dojo.connect(map, 'onLayersAddResult', function(results) {
//		var toc = new agsjs.dijit.TOC({
//			map : map,
//			layerInfos : [ {
//				layer : planodir,
//				title : "Plano Diretor"
//			}, {
//				layer : infra,
//				title : "Infraestrutura",
//				collapsed : true
//			}, {
//				layer : condicionantes,
//				title : "Condicionantes",
//				collapsed : true
//			}, {
//				layer : ambiente,
//				title : "Meio Ambiente",
//				collapsed : true
//			}, 														
//]
//		}, 'tocDiv');
//		toc.startup();
	
//	   planodir.setVisibleLayers([1,2,3,4,5,6,7,8,9]);
		

//		planodir.setVisibleLayers([ 0 ]);
		
		
//		planodir.setVisibleLayers([ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13,
//				14, 15, 16, 17, 18, 19, 27 ]);

	});

	map.addLayers([ planodir ]);
	
//	map.addLayers([ planodir, infra, condicionantes, ambiente ]);	

	dojo.connect(map, "onLoad", mapReady);

	dojo.connect(map.infoWindow, "onShow", function() {
		dijit.byId("tabs").resize();
	});

	dojo.connect(map, "onLoad", function(theMap) {
		var scalebar = new esri.dijit.Scalebar({
			map : map,
			// "dual" displays both miles and kilmometers
			// "english" is the default, which displays miles
			// use "metric" for kilometers
			scalebarUnit : "metric"
		});
	});

	// resize the map when the browser resizes - view the 'Resizing and
	// repositioning the map' section in
	// the following help topic for more details
	// http://help.esri.com/EN/webapi/javascript/arcgis/help/jshelp_start.htm#jshelp/inside_guidelines.htm
	var resizeTimer;

	dojo.connect(map, 'onLoad', function(theMap) {

		selectionLayer = new esri.layers.GraphicsLayer();
		selectionLayer.setRenderer(new esri.renderer.SimpleRenderer(
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([ 255, 255, 0, 0 ]), 3)));

		map.addLayer(selectionLayer);

		var sls = new esri.symbol.SimpleLineSymbol(
				esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([ 255,
						255, 0 ]), 4);

		var sfs = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_SOLID, sls, new dojo.Color([
						255, 255, 0, 0.0 ]));

		var defaultRenderer = new esri.renderer.SimpleRenderer(sfs);

		map.graphics.setRenderer(defaultRenderer);

		dojo.connect(dijit.byId('map'), 'resize', function() { // resize the
																// map if the
																// div is
																// resized
			clearTimeout(resizeTimer);
			resizeTimer = setTimeout(function() {
				map.resize();
				map.reposition();
			}, 500);
		});

		executeTask(true);

	});

	createBasemapGallery();
	

}

function createBasemapGallery() {
	// manually create basemaps to add to basemap gallery
	var basemaps = [];

	var noMapLayer = new esri.dijit.BasemapLayer(
			{
				url : "http://server.arcgisonline.com/ArcGIS/rest/services/World_Physical_Map/MapServer"
			});

	var noMapBasemap = new esri.dijit.Basemap({
		layers : [ noMapLayer ],
		title : "Sem Mapa de Fundo",
		thumbnailUrl : thumbnailUrl() + "/img/semmapa.jpg"
	});
	basemaps.push(noMapBasemap);

	var satelliteLayer = new esri.dijit.BasemapLayer(
			{
				url : "http://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer"
			});
	var satelliteBasemap = new esri.dijit.Basemap({
		layers : [ satelliteLayer ],
		title : "Imagem de Satélite",
		thumbnailUrl : thumbnailUrl() + "/img/imagery.jpg"
	});
	basemaps.push(satelliteBasemap);

	var cincoMilLayer = new esri.dijit.BasemapLayer({
		url : MAPA_5000
	});
	var cincoMilBasemap = new esri.dijit.Basemap({
		layers : [ cincoMilLayer ],
		title : "Raster 1:5000",
		thumbnailUrl : thumbnailUrl() + "/img/5000.jpg"
	});
	basemaps.push(cincoMilBasemap);

	var basemapGallery = new esri.dijit.BasemapGallery({
		showArcGISBasemaps : false,
		basemaps : basemaps,
		map : map
	}, "basemapGallery");
	basemapGallery.startup();

	dojo.connect(basemapGallery, "onError", function(error) {
		console.log(error);
	});

	dojo.connect(map, "onLoad", function(theMap) {
		var scalebar = new esri.dijit.Scalebar({
			map : map,
			// "dual" displays both miles and kilmometers
			// "english" is the default, which displays miles
			// use "metric" for kilometers
			scalebarUnit : "metric"
		});
	});

}

function chamadaEPM(link) {

	window.open(link);

}

function mapReady(map) {
	dojo.connect(map, "onClick", executeIdentifyTask);
	// create identify tasks and setup parameters
	identifyTask = new esri.tasks.IdentifyTask(CAMADAS);

	identifyParams = new esri.tasks.IdentifyParameters();
	identifyParams.tolerance = 3;
	identifyParams.returnGeometry = true;
	identifyParams.layerIds = [ MOSAICO_DEP, MOSAICO_1_1000, CAMADA_DMAE ];
	identifyParams.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
	identifyParams.width = map.width;
	identifyParams.height = map.height;

	map.infoWindow.resize(415, 250);
	map.infoWindow.setContent(dijit.byId("tabs").domNode);
	map.infoWindow.setTitle("Informações");

//	if (this.data._subLayerInfos) {
//        //acm added to collapse groups at the start
//        this.toggler.hide();
//        //end acm addition
//	}
//	
//    dojo.addClass(this.iconNode, a ? "dijitTreeExpandoClosed" : "dijitTreeExpandoClosed");
		 
		 
}

function executeIdentifyTask(evt) {
	identifyParams.geometry = evt.mapPoint;
	identifyParams.mapExtent = map.extent;

	identifyTask.execute(identifyParams, function(idResults) {
		addToMap(idResults, evt);
	});
}
function addToMap(idResults, evt) {
	depResults = {
		displayFieldName : null,
		features : []
	};
	alinhamentoResults = {
		displayFieldName : null,
		features : []
	};
	dmaeEsgotoResults = {
		displayFieldName : null,
		features : []
	};

	dmaeAguaResults = {
			displayFieldName : null,
			features : []
		};
	
	for ( var i = 0, il = idResults.length; i < il; i++) {
		var idResult = idResults[i];

		if (idResult.layerId == MOSAICO_DEP) {
			if (!depResults.displayFieldName) {
				depResults.displayFieldName = idResult.displayFieldName;
			}
			depResults.features.push(idResult.feature);
			if (!dmaeAguaResults.displayFieldName) {
				dmaeAguaResults.displayFieldName = idResult.displayFieldName;
			}
			dmaeAguaResults.features.push(idResult.feature);			
		} else if (idResult.layerId == MOSAICO_1_1000) {
			if (!alinhamentoResults.displayFieldName) {
				alinhamentoResults.displayFieldName = idResult.displayFieldName;
			}
			alinhamentoResults.features.push(idResult.feature);
		} else if (idResult.layerId == CAMADA_DMAE) {
			if (!dmaeEsgotoResults.displayFieldName) {
				dmaeEsgotoResults.displayFieldName = idResult.displayFieldName;
			}
			dmaeEsgotoResults.features.push(idResult.feature);
		}
	}
	dijit.byId("depTab").setContent(layerTabContent(depResults, "depResults"));
	dijit.byId("alinhamentoTab").setContent(
			layerTabContent(alinhamentoResults, "alinhamentoResults"));

	dijit.byId("dmaeEsgotoTab").setContent(
			layerTabContent(dmaeEsgotoResults, "dmaeEsgotoResults"));

	dijit.byId("dmaeAguaTab").setContent(
			layerTabContent(dmaeAguaResults, "dmaeAguaResults"));

	map.infoWindow.show(evt.screenPoint, map
			.getInfoWindowAnchor(evt.screenPoint));
}

function layerTabContent(layerResults, layerName) {
	var content = "";
	var url = "";
	switch (layerName) {
	case "depResults":

		content = "<table border='0'>";
		for ( var i = 0, il = layerResults.features.length; i < il; i++) {
			url = thumbnailUrl() + "/pdf.seam?carta="
					+ layerResults.features[i].attributes['CARTA'] + "&layerName=" + layerName;
			content += "<BR><tr><td> Carta: "
					+ layerResults.features[i].attributes['CARTA']
					+ " <a href=" + url
					+ " target='_blank' /> Visualizar </a></td>";
		}
		content += "</tr></table>";

		break;

	case "alinhamentoResults":
		content = "<table border='0'>";
		for ( var i = 0, il = layerResults.features.length; i < il; i++) {
			url = thumbnailUrl() + "/pdf.seam?carta="
					+ layerResults.features[i].attributes['CHAVE'] + "&layerName=" + layerName;
			content += "<BR><tr><td> Alinhamento: "
					+ layerResults.features[i].attributes['CHAVE']
					+ " <a href=" + url
					+ " target='_blank' /> Visualizar </a></td>";
		}
		content += "</tr></table>";
		break;
	case "dmaeAguaResults":
		content = "<table border='0'>";
		for ( var i = 0, il = layerResults.features.length; i < il; i++) {
			url = thumbnailUrl() + "/pdf.seam?carta="
					+ layerResults.features[i].attributes['CARTA'] + "&layerName=" + layerName;
			content += "<BR><tr><td> Carta de Água: "
					+ layerResults.features[i].attributes['CARTA']
					+ " <a href=" + url
					+ " target='_blank' /> Visualizar </a></td>";
		}
		content += "</tr></table>";
		break;
	case "dmaeEsgotoResults":
		content = "<table border='0'>";
		for ( var i = 0, il = layerResults.features.length; i < il; i++) {
			
			var inicio = layerResults.features[i].attributes['TIF_FILE'].lastIndexOf("PERFIS_ESGOTO\\");
			var ultimaLetra = layerResults.features[i].attributes['TIF_FILE'].toLowerCase().lastIndexOf("f");
			var tifFile = layerResults.features[i].attributes['TIF_FILE'].substring(inicio + 14, ultimaLetra + 1);
			
			url = "http://geopmpa2.pmpa.ad/DMAE/ESGOTO/RASTER/PERFIS_ESGOTO/" + tifFile.replace("\\", "/");
//			alert(layerResults.features[i].attributes['TIF_FILE']);
//			alert("inicio: " + inicio);
//			alert("ultimaLetra: " + ultimaLetra);
//			alert("tifFile: " + tifFile);
//			alert("url: " + url);
			
			content += "<BR><tr><td> Comprimento: "
					+ layerResults.features[i].attributes['COMPRIMENT']
					+ " </td></tr>" 
					+ "<tr><td> Diâmetro: "
					+ layerResults.features[i].attributes['DIAMETRO']
					+ "</td></tr>"			
					+ "<tr><td> Destino: "
					+ layerResults.features[i].attributes['DESTINO']
					+ "</td></tr>"			
					+ "<tr><td> Material: "
					+ layerResults.features[i].attributes['MATERIAL']
					+ "</td></tr>"			
					+ "<tr><td> Ativa: "
					+ layerResults.features[i].attributes['ATIVA'] + "</td>";
			if (ultimaLetra > 0 ) {
				content += "</tr><BR><tr><td>"
						+ " <a href=" + url
						+ " target='_blank' /> Visualizar Perfil  </a></td>";
			}
		}
		content += "</tr></table>";
		break;
		
	}
	return content;
}

dojo.addOnLoad(init);
