package com.yonder.chat;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SecondLayout extends VerticalLayout implements Broadcaster.BroadcastListener{
	private UI u;
	final private String name;
	private TextField txt;
	public static Vector<User> people=new Vector<User>();
	final private Grid grid;
	public SecondLayout(UI ui,String nume) {
		this.u=ui;
		this.name=nume;
		grid = new Grid();
		grid.addColumn("Nume",String.class);
		grid.addColumn("Mesaj",String.class);
		for(int i=0;i<people.size();i++)
			grid.addRow(people.get(i).getName(),people.get(i).getMesaj());
		this.addComponent(grid);
		HorizontalLayout h=new HorizontalLayout();
		
		txt=new TextField();
		Button send=new Button("Send");
		h.addComponent(txt);
		h.addComponent(send);
		this.addComponent(h);
		Button back=new Button("Log out");
		this.addComponent(back);
		back.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				u.setContent(new MainLayout(u));

			}
		});
		send.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				people.add(new User(name,txt.getValue()));
				//grid.addRow(people.get(people.size()-1).getName(),people.get(people.size()-1).getMesaj());
				Broadcaster.broadcast(new User(name,txt.getValue()));
			}
		});
		Broadcaster.register(this);
	}
	@Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }

	@Override
	public void receiveBroadcast(final User u) {
	        // Must lock the session to execute logic safely
		//access(new Runnable() {
			 //@Override
			 //public void run() {
				 this.grid.addRow(people.get(people.size()-1).getName(),people.get(people.size()-1).getMesaj());
	}
		 //});
	    //}
}