package com.idividends.ui.admin.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/login")
@Theme("valo")
public class LoginUI extends UI {

	private static final long serialVersionUID = -272746643019390677L;

	@Autowired
	VaadinSharedSecurity vaadinSecurity;

	private TextField userName;
	private PasswordField passwordField;
	private Label loginFailedLabel;
	private Label loggedOutLabel;

	private Button login;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		userName = new TextField("username");
		content.addComponent(userName);
		passwordField = new PasswordField("password");
		content.addComponent(passwordField);
		login = new Button("Login");
		login.addClickListener(e -> {
			login();

		});
		content.addComponent(login);
		setContent(content);

	}

	private void login() {
		try {
			// vaadinSecurity.login(userName.getValue(),
			// passwordField.getValue(), rememberMe.getValue());
			vaadinSecurity.login(userName.getValue(), passwordField.getValue());
		} catch (AuthenticationException ex) {
			userName.focus();
			userName.selectAll();
			passwordField.setValue("");
			loginFailedLabel.setValue(String.format("Login failed: %s", ex.getMessage()));
			loginFailedLabel.setVisible(true);
			if (loggedOutLabel != null) {
				loggedOutLabel.setVisible(false);
			}
		} catch (Exception ex) {
			Notification.show("An unexpected error occurred", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
			LoggerFactory.getLogger(getClass()).error("Unexpected error while logging in", ex);
		} finally {
			login.setEnabled(true);
		}

	}

}
