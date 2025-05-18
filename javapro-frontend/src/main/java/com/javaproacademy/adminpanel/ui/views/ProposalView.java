package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.forms.ProposalForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("proposal-form")
public class ProposalView extends VerticalLayout {
    public ProposalView(){
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        //getStyle().set("background-color", "#f5f5f5");

        ProposalForm proposalForm = new ProposalForm();
        Div card = new Div(proposalForm);
        card.getStyle().set("padding", "2rem");
        card.getStyle().set("border-radius", "8px");
        card.getStyle().set("box-shadow", "0 4px 12px rgba(0, 0, 0, 0.15)");
        card.getStyle().set("background-color", "white");
        card.setWidth("400px");
        add(proposalForm);
    }
}
