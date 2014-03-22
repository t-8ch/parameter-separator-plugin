/*
 *Copyright (c) 2013 Costco, Vimil Saju
 *Copyright (c) 2014 Mike Chmielewski
 *See the file license.txt for copying permission.
 */

package jenkins.plugins.parameter_separator;

import java.util.Date;
import java.util.UUID;

import hudson.Extension;
import hudson.model.ParameterValue;
import hudson.model.ParameterDefinition;
import hudson.util.FormValidation;

import org.kohsuke.stapler.*;
import net.sf.json.JSONObject;

public class ParameterSeparatorDefinition extends ParameterDefinition {

	@Extension
	public static class ParameterSeparatorDescriptor extends ParameterDescriptor {
		
		@Override
		public String getDisplayName() {
			return Messages.ParameterSeparatorDefinition_DisplayName();
		}
	}

	private static final String SEPARATOR_ELEMENT = "<hr style=\"${SEPARATOR_STYLE}\" />";
	private static final String SEPARATOR_STYLE = "margin-top:10px;margin-bottom:10px;";

	public ParameterValue getDefaultParameterValue() {
		return new ParameterSeparatorValue(getName(), buildSeparatorElement());
	}

	@DataBoundConstructor
	public ParameterSeparatorDefinition(final String name) {
		super("separator-" + UUID.randomUUID().toString());
	}

	@Override
	public ParameterValue createValue(final StaplerRequest request) {
		return new ParameterSeparatorValue(getName(), buildSeparatorElement());
	}

	@Override
	public ParameterValue createValue(final StaplerRequest request, final JSONObject jObj) {
		return new ParameterSeparatorValue(getName(), buildSeparatorElement());
	}

	private String buildSeparatorElement() {
		return SEPARATOR_ELEMENT.replace("${SEPARATOR_STYLE}", SEPARATOR_STYLE);
	}
}