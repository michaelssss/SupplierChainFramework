package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.Contact;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/8/29
 */
@Controller
@RequestMapping("Contacts")
public class ContactsController {
    @Autowired
    private CompanyRepository repository;

    @ResponseBody
    @RequestMapping(value = "Contacts/add", method = RequestMethod.POST)
    public Response addContactInfo(@RequestParam(value = "id") Long id, @RequestBody Contact contact) {
        CompanyImpl company = repository.findOne(id);
        company.addContacts(contact);
        company.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("Contacts/update")
    public Response updateContactInfo(@RequestBody Contact contact) {
        contact.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("Contacts/delete")
    public Response deleteContactInfoById(@RequestBody Contact contact) {
        contact.deleteInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("Contacts/queryAll")
    public Response queryContactInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getContactSet());
    }

}
