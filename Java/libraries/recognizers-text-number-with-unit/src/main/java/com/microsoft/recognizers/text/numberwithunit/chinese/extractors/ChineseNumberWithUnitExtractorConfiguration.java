package com.microsoft.recognizers.text.numberwithunit.chinese.extractors;

import com.microsoft.recognizers.text.CultureInfo;
import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.number.chinese.ChineseNumberExtractorMode;
import com.microsoft.recognizers.text.number.chinese.extractors.NumberExtractor;
import com.microsoft.recognizers.text.numberwithunit.extractors.INumberWithUnitExtractorConfiguration;
import com.microsoft.recognizers.text.numberwithunit.resources.ChineseNumericWithUnit;
import com.microsoft.recognizers.text.numberwithunit.resources.EnglishNumericWithUnit;
import com.microsoft.recognizers.text.utilities.DefinitionLoader;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ChineseNumberWithUnitExtractorConfiguration implements INumberWithUnitExtractorConfiguration {
    private final CultureInfo cultureInfo;
    private final IExtractor unitNumExtractor;
    private final Pattern compoundUnitConnectorRegex;
    private Map<Pattern, Pattern> ambiguityFiltersDict;

    protected ChineseNumberWithUnitExtractorConfiguration(CultureInfo cultureInfo) {
        this.cultureInfo = cultureInfo;

        this.unitNumExtractor = new NumberExtractor(ChineseNumberExtractorMode.ExtractAll);
        this.compoundUnitConnectorRegex =
                Pattern.compile(ChineseNumericWithUnit.CompoundUnitConnectorRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

        this.ambiguityFiltersDict = DefinitionLoader.loadAmbiguityFilters(ChineseNumericWithUnit.AmbiguityFiltersDict);
    }

    public CultureInfo getCultureInfo() {
        return this.cultureInfo;
    }

    public IExtractor getUnitNumExtractor() {
        return this.unitNumExtractor;
    }

    public String getBuildPrefix() {
        return ChineseNumericWithUnit.BuildPrefix;
    }

    public String getBuildSuffix() {
        return ChineseNumericWithUnit.BuildSuffix;
    }

    public String getConnectorToken() {
        return ChineseNumericWithUnit.ConnectorToken;
    }

    public Pattern getCompoundUnitConnectorRegex() {
        return this.compoundUnitConnectorRegex;
    }
    
    public Pattern getAmbiguousUnitNumberMultiplierRegex() {
        return null;
    }

    public abstract String getExtractType();

    public abstract Map<String, String> getSuffixList();

    public abstract Map<String, String> getPrefixList();
    
    public abstract List<String> getAmbiguousUnitList();

    public Map<Pattern, Pattern> getAmbiguityFiltersDict() {
        return ambiguityFiltersDict;
    }
}
