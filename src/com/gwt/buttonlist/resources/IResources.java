package com.gwt.buttonlist.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface IResources extends ClientBundle {

	@Source("phone.png")
	ImageResource phone();

	@Source("mobile.png")
	ImageResource mobile();

	@Source("letter.png")
	ImageResource letter();

	@Source("panel.css")
	ICssResource getStyle();

}
