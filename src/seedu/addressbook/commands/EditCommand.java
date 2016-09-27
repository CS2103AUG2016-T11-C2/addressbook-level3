package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Edits a person's information identified by name
 */

public class EditCommand extends Command {

	public static final String COMMAND_WORD = "edit";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits the person identified by index.\n\t"
			+ "Parameters: INDEX PARAMETER_TO_EDIT NEW_INFORMATION" + "Example: " + COMMAND_WORD + "1 name Jack Lim";

	public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Editted Person: %1$s";

	private String parameter;
	private String newInfo;
	
	public EditCommand(int targetVisibleIndex, String parameter, String newInfo) {
		super (targetVisibleIndex);
		this.parameter = parameter;
		this.newInfo = newInfo;
	}
	
    @Override
    public CommandResult execute() {
        	final ReadOnlyPerson target = getTargetPerson();
        	Person toEditPerson = addressBook.getPerson(target);
        	
        	
        	switch (parameter) {
        	case "name":
        	toEditPerson.getName().editName(newInfo);
        	
        	case "address":
        		toEditPerson.getAddress().editAdress(newInfo);
        	
        	case "phone":
        		toEditPerson.getPhone().editPhone(newInfo);
        		
        	case "email":
        		toEditPerson.getEmail().editEmail(newInfo);
        	}
        	
        	return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));    
    }
    
}
