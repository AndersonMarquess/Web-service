package com.andersonmarques.estoquews.models.item;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// Configuração para serializar os atributos,
// por padrão apenas membros com visibilidade pública é serializado.
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaItens {

	// Cada elemento da lista será embrulhado com <item></item>
	@XmlElement(name = "item")
	private List<Item> itens;

	public ListaItens(List<Item> itens) {
		this.itens = itens;
	}

	ListaItens() {
	}

	public List<Item> getItens() {
		return itens;
	}

}
