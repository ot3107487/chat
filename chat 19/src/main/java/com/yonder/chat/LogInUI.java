package com.yonder.chat;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.*;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Mihai B.
 */
@Theme("mytheme")
@Widgetset("com.yonder.chat.MyAppWidgetset")
@Push
@PreserveOnRefresh
public class LogInUI extends UI  implements Broadcaster.BroadcastListener {
	private ChatUI chatUI;
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
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		final FormLayout hLayout = new FormLayout();
		hLayout.setMargin(true);
		layout.addComponent(hLayout);
		
		final TextField username = new TextField("Username");
		username.setIcon(FontAwesome.USER);
		username.setValue("");
		hLayout.addComponent(username);
		
		final PasswordField password = new PasswordField("Password");
		password.setIcon(FontAwesome.KEY);
		hLayout.addComponent(password);
		
		
		Button button = new Button("Log In");
		button.addClickListener(new Button.ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				//layout.addComponent(new Label(username.getValue()));
				User user = new User(username.getValue(), password.getValue());
				chatUI = new ChatUI(user);
				chatUI.addMessages();
				setContent(chatUI);
				
				
			}
		});
		hLayout.addComponent(button);
		Broadcaster.register(this);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = LogInUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	@Override
	public void receiveBroadcast(final String username, final String message) {
		// TODO Auto-generated method stub
		 // Must lock the session to execute logic safely
        access(new Runnable() {
            @Override
            public void run() {
                // Show it somehow
            	chatUI.addMessage(username, message);
            }
        });
	}
	
	// Must also unregister when the UI expires
    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }
}
