package com.javaproacademy.adminpanel.ui.forms;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class ProposalForm extends Composite<FormLayout> {

    public ProposalForm() {

        var titleField = new TextField("Title");

        var proposalTypeField = new ComboBox<ProposalType>("Proposal Type");
        proposalTypeField.setItems(ProposalType.values());

        var descriptionField = new TextArea("Description");

        var startDateField = new DatePicker("Start Date");

        var endDateField = new DatePicker("End Date");

        // Configure the form
        var formLayout = getContent();
        formLayout.add(titleField);
        formLayout.add(proposalTypeField);
        formLayout.add(descriptionField, 2);
        formLayout.add(startDateField);
        formLayout.add(endDateField);
    }
}