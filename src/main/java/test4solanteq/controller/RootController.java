package test4solanteq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test4solanteq.model.PositionModel;
import test4solanteq.service.EmployeeService;
import test4solanteq.service.PositionService;
import test4solanteq.transfer.request.AddEmployeeRequestBody;
import test4solanteq.transfer.request.AddPositionRequestBody;
import test4solanteq.transfer.request.SearchEmployeeRequestBody;
import test4solanteq.transfer.response.AddPositionResponseData;
import test4solanteq.transfer.response.SearchEmployeeResponseData;

import javax.validation.Valid;
import java.util.List;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"HardcodedFileSeparator", "unused"})
@RestController
public class RootController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;

    @RequestMapping(value = "/addemp", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addEmployee(
            @RequestBody @Valid final AddEmployeeRequestBody employee) {
        employeeService.addUser(employee.getLastName(), employee.getFirstName(),
                employee.getMiddleName(), employee.getBirthDate(), employee.getPositionId());
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<SearchEmployeeResponseData> searchUser(
            @RequestBody @Valid final SearchEmployeeRequestBody employee) {
        return employeeService.searchUser(employee.getLastName(), employee.getFirstName(),
                employee.getMiddleName(), employee.getBirthDate(), employee.getPositionId());
    }

    @RequestMapping(value = "/addpos", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AddPositionResponseData addPosition(
            @RequestBody @Valid final AddPositionRequestBody position) {
        return new AddPositionResponseData(positionService.addPosition(position.getName()));
    }

    @RequestMapping(value = "/getpos", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PositionModel> getAllPositions() {
        return positionService.getAllPositions();
    }
}