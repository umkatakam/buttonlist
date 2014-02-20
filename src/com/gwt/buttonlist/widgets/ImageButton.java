package com.gwt.buttonlist.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwt.buttonlist.resources.IButtonResources;

public class ImageButton extends Composite implements HasValue<Boolean> {

	private static ImageButtonUiBinder uiBinder = GWT.create(ImageButtonUiBinder.class);

	interface ImageButtonUiBinder extends UiBinder<Widget, ImageButton> {
	}

	@UiField
	IButtonResources resource;

	@UiField
	HTMLPanel imageContainer;

	@UiField
	HTMLPanel selectedIcon;

	@UiField
	FocusPanel panel;

	Boolean isSelected = false;

	public ImageButton() {
		initWidget(uiBinder.createAndBindUi(this));
		resource.getStyle().ensureInjected();
		setEnabled(false);
	}

	@UiHandler("panel")
	void onButtonClicked(ClickEvent event) {
		setValue(getValue() ? false : true);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public Boolean getValue() {
		return isSelected;
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, true);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {

		final boolean previousSelectionValue = isSelected;

		isSelected = value;

		selectedIcon.setVisible(isSelected);

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, previousSelectionValue, isSelected);
		}

	}

	public void setEnabled(boolean enabled) {
		String valueAsString = String.valueOf(!enabled);
		panel.getElement().setAttribute("disabled", valueAsString);
		imageContainer.getElement().setAttribute("disabled", valueAsString);
		selectedIcon.getElement().setAttribute("disabled", valueAsString);
	}

	public void setImage(ImageResource imageResource) {
		imageContainer.add(new Image(imageResource));
	}

}
