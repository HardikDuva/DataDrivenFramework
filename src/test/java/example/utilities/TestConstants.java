package example.utilities;

public final class TestConstants {

	/**
	 * There should be no instance of this class.
	 */
	private TestConstants() { }

	/**
	 * The
	 */
	public static final boolean RUN_HEADLESS
			= Boolean.parseBoolean(FrameworkConfig.get("RUN_HEADLESS"));

	/**
	 * The
	 */
	public static final boolean TEST_RESULT_ADD_TESTRAIL
			= Boolean.parseBoolean(FrameworkConfig.get("TEST_RESULT_ADD_TESTRAIL"));

}
