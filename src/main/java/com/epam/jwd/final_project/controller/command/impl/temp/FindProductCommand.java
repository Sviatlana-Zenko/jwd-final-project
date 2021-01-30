package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;

import java.util.List;

public class FindProductCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/found-product.jsp"); // ???????????

        String title = requestContext.getParameter("title-to-find");

//        List<CinemaProduct> productList = CinemaProductDaoImpl.getInstance().findByTitle(title);
//        System.out.println(productList);

//        requestContext.setAttributes("products", productList);

        return responseContext;
    }
}
