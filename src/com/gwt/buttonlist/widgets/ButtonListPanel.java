package com.gwt.buttonlist.widgets;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ButtonListPanel extends Composite implements HasWidgets {

	private static ButtonListPanelUiBinder uiBinder = GWT.create(ButtonListPanelUiBinder.class);

	interface ButtonListPanelUiBinder extends UiBinder<Widget, ButtonListPanel> {
	}

	@UiField
	HTMLPanel container;

	ArrayList<ImageButton> listOfButtons = new ArrayList<ImageButton>();
	ArrayList<ImageButton> selectedList = new ArrayList<ImageButton>();

	boolean isMultiSelect = true;

	public ButtonListPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setMultiSelect(boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	@Override
	public void add(Widget w) {
		assert (w instanceof ImageButton);
		final ImageButton button = (ImageButton) w;
		button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				Boolean isSelected = event.getValue();
				if (isSelected) {
					selectedList.add(button);
					if (!isMultiSelect) {
						for (ImageButton imageButton : listOfButtons) {
							if (imageButton != button) {
								imageButton.setValue(false, false);
							}
						}
					}
				} else {
					selectedList.remove(button);
				}
			}
		});
		listOfButtons.add(button);
		container.add(button);

	}

	@Override
	public void clear() {
		container.clear();
		listOfButtons.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		assert (w instanceof ImageButton);
		listOfButtons.remove(w);
		return container.remove(w);
	}

}
