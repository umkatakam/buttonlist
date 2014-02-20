package com.gwt.buttonlist.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface IButtonResources extends ClientBundle {

	@Source("selected.png")
	ImageResource selected();

	@Source("button.css")
	IButtonCssResource getStyle();

}
