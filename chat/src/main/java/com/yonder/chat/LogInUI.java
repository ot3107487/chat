package com.yonder.chat;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Mihai B.
 */
@Theme("mytheme")
@Widgetset("com.yonder.chat.MyAppWidgetset")
@Push
public class LogInUI extends UI  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * The "Main method".
	 *
	 * This is the entry point method executed to initialize and configure the
	 * visible user interface. Executed on every browser reload because a new
	 * instance is created for each web page loaded.
	 */
	@Override
	protected void init(VaadinRequest request) {
		final MainLayout m=new MainLayout(this);
		setContent(m);
				
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = LogInUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
}
