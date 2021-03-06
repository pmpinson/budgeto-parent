package org.pmp.budgeto.test.config;

import org.pmp.budgeto.common.domain.DomainTools;
import org.pmp.budgeto.common.domain.DomainToolsImpl;
import org.pmp.budgeto.common.tools.DateTools;
import org.pmp.budgeto.common.tools.DateToolsImpl;
import org.pmp.budgeto.common.tools.LocaleTools;
import org.pmp.budgeto.common.tools.LocaleToolsImpl;
import org.pmp.budgeto.common.tools.ToolsConfig;
import org.pmp.budgeto.common.tools.TranslatorTools;
import org.pmp.budgeto.common.tools.TranslatorToolsImpl;
import org.pmp.budgeto.common.tools.ValidatorTools;
import org.pmp.budgeto.common.tools.ValidatorToolsImpl;

/**
 * Helper to configure test scope and inject dependencies
 */
public class TestConfig {

    public static final DateTools dateTools = new DateToolsImpl();

    public static final LocaleTools localeTools = new LocaleToolsImpl();

    public static final ValidatorTools validatorTools = new ValidatorToolsImpl();

    public static final TranslatorTools translatorTools = new TranslatorToolsImpl(new ToolsConfig().messageSource(), localeTools);

    public static final DomainTools domainTools = new DomainToolsImpl(translatorTools, validatorTools);

    public static void init() {
        ((LocaleToolsImpl) localeTools).init();
    }

}
