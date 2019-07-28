package com.andersonmarques.estoquews.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UtilitarioDate extends XmlAdapter<String, Date> {
	private String padraoDaData = "dd/MM/yyyy";

	/**
	 * Transforma uma {@link String} no padrão dd/MM/yyyy em um {@link Date}.
	 * 
	 * @param data
	 * @throws ParseException
	 */
	@Override
	public Date unmarshal(String data) throws ParseException {
		return new SimpleDateFormat(padraoDaData).parse(data);
	}

	/**
	 * Transforma um {@link Date} em uma {@link String} no padrão dd/MM/yyyy.
	 * 
	 * @param data
	 * @throws ParseException
	 */
	@Override
	public String marshal(Date data) throws ParseException {
		return new SimpleDateFormat(padraoDaData).format(data);
	}
}