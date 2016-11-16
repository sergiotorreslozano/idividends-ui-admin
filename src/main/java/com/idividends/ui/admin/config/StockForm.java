package com.idividends.ui.admin.config;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.idividends.ui.admin.dto.StockDto;
import com.idividends.ui.admin.service.StockService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form
 * in multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for
 * all your forms - less code, better UX. See e.g. AbstractForm in Virin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class StockForm extends FormLayout {

	private static final long serialVersionUID = 8611530457039127133L;

	@Autowired
	private StockService stockService;
	/**
	 * The currently edited customer
	 */
	private StockDto stock;

	/* Fields to edit properties in Customer entity */
	private TextField symbol = new TextField("symbol");
	private TextField name = new TextField("name");
	private TextField market = new TextField("market");

	/* Action buttons */
	private Button save = new Button("Save", FontAwesome.SAVE);
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("Delete", FontAwesome.TRASH_O);
	private CssLayout actions = new CssLayout(save, cancel, delete);

	@PostConstruct
	public void init() {

		addComponents(name, symbol, market, actions);
		setSizeUndefined();
		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> stockService.save(stock));
		delete.addClickListener(e -> stockService.delete(stock));
		cancel.addClickListener(e -> editStock(stock));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editStock(StockDto c) {
		final boolean persisted = c.getSymbol() != null && !c.getSymbol().isEmpty();
		if (persisted) {
			// Find fresh entity for editing
			stock = stockService.findStock(c.getSymbol());
		} else {
			stock = c;
		}
		delete.setVisible(persisted);
		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		BeanFieldGroup.bindFieldsUnbuffered(stock, this);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		name.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
		cancel.addClickListener(e -> h.onChange());
	}

}