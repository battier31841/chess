package gmfb.chess.uitl.validation;

public interface Validator<T>
{
	public void validate(T t) throws Exception;
}
