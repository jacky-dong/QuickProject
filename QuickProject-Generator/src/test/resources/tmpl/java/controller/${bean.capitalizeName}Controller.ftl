package ${config.javaPackageName}${module.name}.controller;

import chanedi.service.EntityService;
import chanedi.util.JSONUtils;
import ${config.javaPackageName}${module.name}.base.controller.BaseController;
import ${config.javaPackageName}${module.name}.model.${bean.capitalizeName};
import ${config.javaPackageName}${module.name}.service.${bean.capitalizeName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/" + ${bean.capitalizeName}Controller.NAME)
public class ${bean.capitalizeName}Controller extends BaseController {

    public static final String NAME = "${bean.name}";

    @Resource
    private ${bean.capitalizeName}Service ${bean.name}Service;

    @Override
    public String getIndexTilesName() {
        return NAME;
    }

    @Override
    public EntityService getEntityService() {
        return ${bean.name}Service;
    }

    @Override
    public Class<?> getEntityClass() {
        return ${bean.capitalizeName}.class;
    }

}