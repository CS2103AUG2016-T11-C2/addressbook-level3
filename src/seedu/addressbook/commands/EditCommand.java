package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Edits a person's information identified by name
 */

public class EditCommand extends Command {

	public static final String COMMAND_WORD = "edit";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits the person identified by name.\n\t"
			+ "Parameters: INDEX PARAMETER_TO_EDIT NEW_INFORMATION" + "Example: " + COMMAND_WORD + "1 name Jack Lim";

	public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Editted Person: %1$s";

	public EditCommand(int targetVisibleIndex) {
		super (targetVisibleIndex);
	}
	
	private Name name;
	private Email email;
	private Address address;
	private Phone phone;
	private final UniqueTagList tag;
	private Person edittedPerson;
	
    @Override
    public CommandResult execute() {
        try {
        	final ReadOnlyPerson target = getTargetPerson();
        	this.name = target.getName();
        	this.email = target.getEmail();
        	this.address = target.getAddress();
        	this.phone = target.getPhone();
        	this.tag = target.getTags();
        	
        	EditParameters(parameter, information);
        	
        	addressBook.removePerson(target);
        	addressBook.addPerson(edittedPerson);
        	
        	return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

    private void EditParameters(String parameter, String information) throws IllegalValueException {
    	switch (parameter) {
    	case "name":
    		this.name = new Name(information);
    	case "email":
    		this.email = new Email(information, false);
    	case "address":
    		this.address = new Address(information, false);
    	case "phone":
    		this.phone = new Phone(information, false);
    	}
    	
    	this.edittedPerson = new Person(this.name, this.phone, this.email, this.address, this.tag);
    }
    

}
