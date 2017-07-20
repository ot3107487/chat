package com.yonder.chat;

import java.util.*;

import com.vaadin.annotations.Push;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@Push
public class ChatUI extends VerticalLayout {

	/**
	 * 
	 */
	
	public static final List<User> messageList = new ArrayList<User>();
	private final Grid grid;
	private final TextField message;
	private Button button;
	private Button logOutButton;
	
	private static final long serialVersionUID = 1L;
	
	public ChatUI(final User user) {
		HorizontalLayout layout = new HorizontalLayout();
		
		grid = new Grid();
		grid.addColumn("Username", String.class);
		grid.addColumn("Message", String.class);
		this.addComponent(grid);
		
		message = new TextField("");
		message.setValue("Write your message here...");
		layout.addComponent(message);
		
		button = new Button("Send");
		
		button.addClickListener(new Button.ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				messageList.add(new User(user.getUsername(), user.getPassword(), message.getValue()));
				//grid.addRow(user.getUsername(), message.getValue());
				Broadcaster.broadcast(user.getUsername(), message.getValue());
				message.setValue("");
			}
		});
		layout.addComponent(button);
		
		logOutButton = new Button("LogOut");
		
		logOutButton.addClickListener(new Button.ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		layout.addComponent(logOutButton);
		this.addComponent(layout);
	}
	
	public void addMessages() {
		for(User u: messageList) {
			grid.addRow(u.getUsername(), u.getMessage());
		}
	}
	
	public void addMessage(String username, String message) {
		grid.addRow(username, message);
	}

}
