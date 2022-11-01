package classes.from.console.project;
import java.util.regex.Pattern;


/**
 * RegexRepertory => Class Repertory for all regex pattern
 * @author Java Generator Team
 *
 */
public class RegexRepertory {
	
	private static final Pattern lettersPattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
	private static final Pattern numbersPattern = Pattern.compile("[0-9]+");
	private static final Pattern specialCharactersPattern = Pattern.compile("^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$");
	private static final Pattern specialCharactersWithoutDotPattern = Pattern.compile("^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,<>\\/?]*$");
	private static final Pattern emailRegex = Pattern.compile("^(.+)@(\\\\S+)$");
	private static final Pattern emailStrictRegex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
	private static final Pattern phoneRegex = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
	private static final Pattern singleDotForDecimals = Pattern.compile("[.]{1}");
	private static final Pattern createTablePattern = Pattern.compile("CREATE TABLE ", Pattern.CASE_INSENSITIVE);
	
	
	// ------------------- GETTERS / SETTERS --------------------- //
	
	/**
	 * getLettersPattern() : get the (only) letters pattern
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getLettersPattern() {
		return lettersPattern;
	}
	
	/**
	 * getNumbersPattern() : get the (only) numbers pattern
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getNumbersPattern() {
		return numbersPattern;
	}
	
	/**
	 * getSpecialSharactersPattern() : get the (only) special characters pattern
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getSpecialSharactersPattern() {
		return specialCharactersPattern;
	}
	
	/**
	 * getSpecialCharactersWithoutDotPattern() : get the (only) special characters pattern (without the dot)
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getSpecialCharactersWithoutDotPattern() {
		return specialCharactersWithoutDotPattern;
	}

	/**
	 * getEmailRegex() : get the email verification pattern (light)
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getEmailRegex() {
		return emailRegex;
	}
	
	/**
	 * getEmailStrictRegex() : get the email verification pattern (advanced)
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getEmailStrictRegex() {
		return emailStrictRegex;
	}
	
	/**
	 * getPhoneRegex() : get the telephone number verification pattern (advanced)
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getPhoneRegex() {
		return phoneRegex;
	}
	
	/**
	 * getSingleDotForDecimals() : Pattern for check the one dot delimiter (for float and doubles values)
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getSingleDotForDecimals() {
		return singleDotForDecimals;
	}

	/**
	 * getCreateTablePattern() : Pattern for check the CREATE TABLE Statement
	 * @return {Pattern} the regex pattern
	 */
	public Pattern getCreateTablePattern() {
		return createTablePattern;
	}
}
