package com.example.IntelliBotBackend.constants;

public class Constants {

    public static final String METHOD_CALLED = "Method called : ";
    public static final String TAG_PROMPT_PREFIX = "Your task is to identify 4 most relevant tags in %s language that would be appropriate for following text. Please provide the tags separated by comma that can be associated with the given text. It should not be numbered and no sentences should be there like \"The most relevant tags are: \", \"The tags are: \", etc. The text is :";

   // public static final String TAG_PROMPT_PREFIX_LANG = "Your task is to identify 4 most relevant tags in  %s language that would be appropriate for following text. Please provide the tags separated in %s by comma that can be associated with the given text. It should not be numbered and no sentences should be there like \"The most relevant tags are: \", \"The tags are: \", etc. The text is :";

    public static final String TAG_FOR_PROMPT_GENERATION="Construct a detailed prompt that I can further use in ChatGPT for creating  %s in %s. Don't write the code for the requirement; only provide the ChatGPT prompt.";

    public static final String TAG_FOR_PROMPT_GEN="Provide appropriate result for this prompt in %s language.";

    public static final String CONVERT_TAG_PROMPT_IN_LANG ="convert this input text %s in %s language";
}
