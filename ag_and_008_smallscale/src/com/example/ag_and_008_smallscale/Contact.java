package com.example.ag_and_008_smallscale;

public class Contact {

	String _name;
	byte[] _image;

	// Empty constructor
	public Contact() {

	}

	// constructor
	public Contact(String name, byte[] image) {
		this._name = name;
		this._image = image;
	}

	// getting name
	public String getName() {
		return this._name;
	}

	// setting name
	public void setName(String name) {
		this._name = name;
	}

	// getting phone number
	public byte[] getImage() {
		return this._image;
	}

	// setting phone number
	public void setImage(byte[] image) {
		this._image = image;
	}
}
