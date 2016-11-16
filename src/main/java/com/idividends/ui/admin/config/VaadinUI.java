package com.idividends.ui.admin.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.idividends.ui.admin.dto.StockDto;
import com.idividends.ui.admin.service.StockService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path = "/admin")
@Theme("valo")
public class VaadinUI extends UI {

	private static final long serialVersionUID = -6431820595009169160L;

	@Autowired
	private StockService stockService;

	@Autowired
	private StockForm form;

	private Grid grid;

	private TextField filter;

	private Button addNewBtn;

	private Button reloadDataBtn;

	@PostConstruct
	public void init() {
		this.grid = new Grid();
		this.filter = new TextField();
		this.addNewBtn = new Button("New stock", FontAwesome.PLUS);
		this.reloadDataBtn = new Button("Reload all");
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		filter.setInputPrompt("filter by symbol...");
		filter.addTextChangeListener(e -> listStocks(e.getText()));
		Button clearFilter = new Button(FontAwesome.TIMES);
		clearFilter.addClickListener(e -> {
			filter.clear();
			listStocks(null);

		});
		CssLayout filtering = new CssLayout();
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		filtering.addComponents(filter, clearFilter);

		HorizontalLayout toolbar = new HorizontalLayout(filtering, addNewBtn, reloadDataBtn);
		toolbar.setSpacing(true);

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSpacing(true);
		main.setSizeFull();
		main.setExpandRatio(grid, 1);
		VerticalLayout layout = new VerticalLayout(toolbar, main);
		layout.setMargin(true);
		layout.setSpacing(true);


		setContent(layout);

		// Configure layouts and components


		grid.setSizeFull();
		// grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("symbol", "market", "name", "lastPrice", "change", "changePercent", "marketCap", "changeYTD",
				"changePercentYTD");
		// Connect selected Customer to editor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				form.setVisible(false);
			} else {
				form.editStock((StockDto) grid.getSelectedRow());
			}
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> form.editStock(new StockDto("", "", "")));
		reloadDataBtn.addClickListener(e -> {
			stockService.reloadData();
			listStocks(null);
		});
		// Listen changes made by the editor, refresh data from backend
		form.setChangeHandler(() -> {
			form.setVisible(false);
			listStocks(filter.getValue());
		});

		// Initialize listing
		listStocks(null);
	}

	public void listStocks(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(new BeanItemContainer<>(StockDto.class, stockService.findStocks()));
		} else {
			grid.setContainerDataSource(new BeanItemContainer<>(StockDto.class, stockService.findStocks(text)));
		}
	}

}