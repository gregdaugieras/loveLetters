
package org.loveLetter.ws.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Eleves {
	private List<Eleve> eleves = new ArrayList<Eleve>();
	
	public List<Eleve> getEleves() {
		return eleves;
	}
	
	@XmlElements(value = { @XmlElement(name="eleve", type=Eleve.class) })
	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}
}