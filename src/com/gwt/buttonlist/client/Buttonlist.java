package com.gwt.buttonlist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwt.buttonlist.resources.IResources;
import com.gwt.buttonlist.widgets.ButtonListPanel;
import com.gwt.buttonlist.widgets.ImageButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Buttonlist implements EntryPoint {

	IResources resource = GWT.create(IResources.class);

	@Override
	public void onModuleLoad() {

		RootPanel rootPanel = RootPanel.get();
		HTMLPanel contentPanel = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);

		resource.getStyle().ensureInjected();

		contentPanel.setStyleName(resource.getStyle().panel());

		final ButtonListPanel listPanel = new ButtonListPanel();
		listPanel.setMultiSelect(false);
		contentPanel.add(listPanel);

		CheckBox checkBox = new CheckBox("Multi-Select");
		checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				listPanel.setMultiSelect(event.getValue());
				refresh(listPanel);
			}
		});

		contentPanel.add(checkBox);

		refresh(listPanel);
		rootPanel.add(contentPanel);
	}

	private void refresh(final ButtonListPanel listPanel) {
		listPanel.clear();
		for (int i = 0; i < 15; i++) {
			ImageButton imageButton = new ImageButton();
			if (i % 3 == 0) {
				imageButton.setImage(resource.phone());
			} else if (i % 3 == 1) {
				imageButton.setImage(resource.mobile());
			} else {
				imageButton.setImage(resource.letter());
			}
			imageButton.setTitle("Button - " + i);
			listPanel.add(imageButton);
		}
	}
}
