package org.pmp.budgeto.domain.budget;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.Validate;
import org.pmp.budgeto.common.controller.ControllerError;
import org.pmp.budgeto.common.controller.DefaultControllerAdvice;
import org.pmp.budgeto.common.domain.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Controler for list and other action on budget.
 */
@RestController
@RequestMapping(value = "budget", produces = DefaultControllerAdvice.JSON_CONTENT_TYPE )
@Api(value = "Budget", description = "Work with budgets")
public class BudgetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetController.class);

    private BudgetDomain budgetDomain;

    @Autowired
    public BudgetController(BudgetDomain budgetDomain) {
        this.budgetDomain = Validate.notNull(budgetDomain);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all budgets", notes = "No")
    @ApiResponses({
            @ApiResponse(code = DefaultControllerAdvice.OK_CODE, message = DefaultControllerAdvice.OK_MSG),
            @ApiResponse(code = DefaultControllerAdvice.NOT_FOUND_CODE, message = DefaultControllerAdvice.NOT_FOUND_MSG, response = ControllerError.class),
            @ApiResponse(code = DefaultControllerAdvice.INTER_ERR_CODE, message = DefaultControllerAdvice.INTER_ERR_MSG, response = ControllerError.class)
    })
    public Set<Budget> findAll() {
        LOGGER.info("get all account");
        return budgetDomain.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new budget", notes = "No")
    @ApiResponses({
            @ApiResponse(code = DefaultControllerAdvice.CREATED_CODE, message = DefaultControllerAdvice.CREATED_MSG),
            @ApiResponse(code = DefaultControllerAdvice.BAD_REQUEST_CODE, message = DefaultControllerAdvice.BAD_REQUEST_MSG, response = ControllerError.class),
            @ApiResponse(code = DefaultControllerAdvice.CONFLICT_CODE, message = DefaultControllerAdvice.CONFLICT_MSG, response = ControllerError.class),
            @ApiResponse(code = DefaultControllerAdvice.NOT_FOUND_CODE, message = DefaultControllerAdvice.NOT_FOUND_MSG, response = ControllerError.class),
            @ApiResponse(code = DefaultControllerAdvice.INTER_ERR_CODE, message = DefaultControllerAdvice.INTER_ERR_MSG, response = ControllerError.class)
    })
    public void add(@RequestBody Budget budget) throws DomainException {
        LOGGER.info("post add for new budget");

        Budget newObject = new Budget().setName(budget.getName()).setNote(budget.getNote());

        budgetDomain.add(newObject);
    }

}
