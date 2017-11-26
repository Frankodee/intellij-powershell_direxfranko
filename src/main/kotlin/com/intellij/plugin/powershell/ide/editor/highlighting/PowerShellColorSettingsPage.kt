package com.intellij.plugin.powershell.ide.editor.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.plugin.powershell.PowerShellIcons
import java.util.*
import javax.swing.Icon

/**
 * Andrey 05/08/17.
 */
class PowerShellColorSettingsPage : ColorSettingsPage {

  @JvmField
  val additionalTags = HashMap<String, TextAttributesKey>()
  @JvmField
  val DESCRIPTORS: Array<AttributesDescriptor> = arrayOf(
      AttributesDescriptor("Keyword", PowerShellSyntaxHighlighter.KEYWORD),
      AttributesDescriptor("Comment", PowerShellSyntaxHighlighter.COMMENT),
      AttributesDescriptor("Number", PowerShellSyntaxHighlighter.NUMBER),
      AttributesDescriptor("String", PowerShellSyntaxHighlighter.STRING),
      AttributesDescriptor("Command name", PowerShellSyntaxHighlighter.COMMAND_NAME),
      AttributesDescriptor("Type name", PowerShellSyntaxHighlighter.TYPE_NAME),
      AttributesDescriptor("Type reference", PowerShellSyntaxHighlighter.TYPE_REFERENCE),
      AttributesDescriptor("Variable name", PowerShellSyntaxHighlighter.VARIABLE_NAME),
      AttributesDescriptor("Property access", PowerShellSyntaxHighlighter.PROPERTY_REFERENCE),
      AttributesDescriptor("Method call", PowerShellSyntaxHighlighter.METHOD_CALL),
      AttributesDescriptor("Method declaration", PowerShellSyntaxHighlighter.METHOD_DECLARATION)
  )

  init {
    additionalTags.put("keyword", PowerShellSyntaxHighlighter.KEYWORD)
    additionalTags.put("comment", PowerShellSyntaxHighlighter.COMMENT)
    additionalTags.put("number", PowerShellSyntaxHighlighter.NUMBER)
    additionalTags.put("string", PowerShellSyntaxHighlighter.STRING)
    additionalTags.put("command name", PowerShellSyntaxHighlighter.COMMAND_NAME)
    additionalTags.put("type name", PowerShellSyntaxHighlighter.TYPE_NAME)
    additionalTags.put("type ref", PowerShellSyntaxHighlighter.TYPE_REFERENCE)
    additionalTags.put("var name", PowerShellSyntaxHighlighter.VARIABLE_NAME)
    additionalTags.put("property access", PowerShellSyntaxHighlighter.PROPERTY_REFERENCE)
    additionalTags.put("method call", PowerShellSyntaxHighlighter.METHOD_CALL)
    additionalTags.put("method declaration", PowerShellSyntaxHighlighter.METHOD_DECLARATION)
  }

  override fun getHighlighter(): SyntaxHighlighter {
    return PowerShellSyntaxHighlighter()
  }

  override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> {
    return additionalTags
  }

  override fun getIcon(): Icon? {
    return PowerShellIcons.FILE
  }

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
    return DESCRIPTORS
  }

  override fun getColorDescriptors(): Array<ColorDescriptor> {
    return ColorDescriptor.EMPTY_ARRAY
  }

  override fun getDisplayName(): String {
    return "PowerShell"
  }

  override fun getDemoText(): String {
    return "foreach (\$<var name>i</var name> in <command name>get-childitem</command name> | <command name>sort-object</command name> length)\n" +
        " {\n" +
        " \$<var name>i</var name>  #highlight\n" +
        " \$<var name>sum</var name> += \$<var name>i</var name>.<property access>length</property access>\n" +
        " }\n" +
        "\n" +
        "# 3) variable name token: '\$<var name>_</var name>.<property access>length</property access> ' and 'get-childitem'\n" +
        "switch -regex -casesensitive (<command name>get-childitem</command name> | sort length)\n" +
        "{\n" +
        " \"^5\" {\"length for \$<var name>_</var name> started with 5\" ; continue}\n" +
        " { \$<var name>_</var name>.<property access>length</property access> > 20000 } {\"length of \$<var name>_</var name> is greater than 20000\"}\n" +
        " default {\"Didn't match anything else...\"}\n" +
        "}\n\n" +
        "class <type name>Person</type name>\n" +
        "{\n" +
        "    [<type ref>int</type ref>]\$<var name>Age</var name>\n" +
        "    Person([<type ref>int</type ref>]\$<var name>a</var name>)\n" +
        "    {\n" +
        "        \$this.<property access>Age<property access> = \$<var name>a</var name>\n" +
        "    }\n" +
        "    <method declaration>DoSomething</method declaration>(\$<var name>x</var name>)\n" +
        "    {\n" +
        "        \$this.<method call>DoSomething</method call>(\$<var name>x</var name>)\n" +
        "    }\n" +
        "}\n" +
        "class <type name>Child</type name>: <type ref>Person</type ref>\n" +
        "{\n" +
        "    [<type ref>string</type ref>]\$<var name>School</var name>\n" +
        "\n" +
        "    <method declaration>Child</method declaration>([<type ref>int</type ref>]\$<var name>a</var name>, [<type ref>string</type ref>]\$<var name>s</var name>): base(\$<var name>a</var name>)\n" +
        "    {\n" +
        "        \$this.<property access>School</property access> = \$<var name>s</var name>\n" +
        "    }\n" +
        "}\n" +
        "[<type ref>Child</type ref>]\$<var name>littleOne</var name> = [<type ref>Child</type ref>]::<method call>new</method call>(10, \"Silver Fir Elementary School\")\n" +
        "Write-Output \"Child's age is \$( \$<var name>littleOne</var name>.<property access>Age</property access> )\""
  }
}