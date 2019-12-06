package text;

/**
 * Class representing text
 */
public class Text extends BaseComplexTextUnit {

    /**
     * Builds String representation of Text. It concatenates paragraphs,
     * code blocks and sentences
     * @return String representation of Text
     */
    @Override
    public String build() {
        StringBuilder textBuilder = new StringBuilder(units.get(0).build());

        for (int i = 1; i < units.size(); i++) {
            if (!(units.get(i - 1) instanceof Paragraph))
                textBuilder.append(" ");

            textBuilder.append(units.get(i).build());
        }

        return textBuilder.toString();
    }

    /**
     * Add unit to Text. Text amy contain Sentence, Code and Paragraph
     * @param unit
     *        Unit to be added
     */
    @Override
    public void addUnit(TextUnit unit) {
        if (!(unit instanceof Sentence || unit instanceof Code || unit instanceof Paragraph))
            throw new IllegalArgumentException("Text may contain only Sentence and Code");

        super.addUnit(unit);
    }
}
