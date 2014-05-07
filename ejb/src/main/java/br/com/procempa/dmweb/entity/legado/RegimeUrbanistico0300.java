package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZCTM0300", schema="SQLDBA")
@NamedQueries({
	@NamedQuery(name= "countReg0300", 
			query="select count( r ) from RegimeUrbanistico0300 as r " +
					"where r.macrozona = :macrozona  " +
					"  and r.ueu = :ueu " +
					"  and r.subunidade   = :subunidade   "),
	@NamedQuery(name= RegimeUrbanistico0300.FIND_REGIME_URBANISTICO_0300, 
			query="select r from RegimeUrbanistico0300 as r " +
					"where r.macrozona = :macrozona  " +
					"  and r.ueu = :ueu " +
					"  and ( r.subunidade   = :subunidade or r.subunidade = 0 ) ")
	})



public class RegimeUrbanistico0300   implements Serializable {
        public static final long serialVersionUID = 1L;
        
    	public static final String FIND_REGIME_URBANISTICO_0300 = "regimeUrbanistico0300.findRegimeUrbanistico";
    	
        private Integer idZCTM0300 = null;
        private Integer obsReg = null;
        private Integer macrozona = null;
        private Integer ueu = null;
        private Integer subunidade = null;

        @Id
        public Integer getIdZCTM0300() {
			return idZCTM0300;
		}
		public void setIdZCTM0300(Integer idZCTM0300) {
			this.idZCTM0300 = idZCTM0300;
		}
		
        @Column(name="OBS_REG", length=5)
        public Integer getObsReg() {
        	return obsReg;
        }
        public void setObsReg(Integer obsReg) {
        	this.obsReg = obsReg;
        }
        @Column(name="mz")
        public Integer getMacrozona() { 
        	return macrozona; 
        }
        public void setMacrozona(Integer macrozona) { 
        	this.macrozona = macrozona; 
        }
        @Column(name="ueu")
        public Integer getUeu() { 
        	return ueu; 
        }
        public void setUeu(Integer ueu) {
        	this.ueu = ueu; 
        }
        @Column(name="subunidade")
        public Integer getSubunidade() { 
        	return subunidade; 
        }
        public void setSubunidade(Integer subunidade) { 
        	this.subunidade = subunidade;
        }

}
