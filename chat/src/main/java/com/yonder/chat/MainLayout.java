package com.yonder.chat;
import org.atmosphere.cpr.MetaBroadcaster.ThirtySecondsCache;

import com.vaadin.shared.ui.label.LabelState;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
//import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout{
	private UI u;
	public MainLayout(UI ui) {
		u=ui;
		final TextField txt=new TextField();
		HorizontalLayout h=new HorizontalLayout();
		Button buton=new Button("Apasa");
		this.addComponent(new Label("Introduceti numele"));
		h.addComponent(txt);
		h.addComponent(buton);
		
		this.addComponent(h);
		buton.addClickListener(new Button.ClickListener() {
		public void buttonClick(ClickEvent event) {
			u.setContent(new SecondLayout(u,txt.getValue()));
	}});
		}
	
	
}