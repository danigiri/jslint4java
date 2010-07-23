package com.googlecode.jslint4java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The result of a JSLint run.
 *
 * @author hdm
 *
 */
public class JSLintResult {

    /**
     * Allow constructing a result class in such a way that we can publish
     * immutable instances, even from outside this package.
     */
    public static class ResultBuilder {

        private final List<JSFunction> functions = new ArrayList<JSFunction>();
        private final List<String> globals = new ArrayList<String>();
        private final List<JSIdentifier> implieds = new ArrayList<JSIdentifier>();
        private final List<Issue> issues = new ArrayList<Issue>();
        private boolean json;
        private final Map<String, Integer> member = new HashMap<String, Integer>();
        private final String name;
        private final List<JSIdentifier> unused = new ArrayList<JSIdentifier>();
        private final List<String> urls = new ArrayList<String>();
        private long duration;

        public ResultBuilder(String name) {
            this.name = name;
        }

        public ResultBuilder addFunction(JSFunction f) {
            functions.add(f);
            return this;
        }

        public ResultBuilder addGlobal(String global) {
            globals.add(global);
            return this;
        }

        public ResultBuilder addImplied(JSIdentifier id) {
            implieds.add(id);
            return this;
        }

        public ResultBuilder addIssue(Issue issue) {
            issues.add(issue);
            return this;
        }

        public ResultBuilder addMember(String name, int count) {
            member.put(name, count);
            return this;
        }

        public ResultBuilder addUnused(JSIdentifier id) {
            unused.add(id);
            return this;
        }

        public ResultBuilder addUrl(String url) {
            urls.add(url);
            return this;
        }

        public JSLintResult build() {
            return new JSLintResult(this);
        }

        public ResultBuilder json(boolean json) {
            this.json = json;
            return this;
        }

        public ResultBuilder duration(long millis) {
            duration = millis;
            return this;
        }

    }

    private final long duration;
    private final List<JSFunction> functions = new ArrayList<JSFunction>();
    private final List<String> globals = new ArrayList<String>();
    private final List<JSIdentifier> implieds = new ArrayList<JSIdentifier>();
    private final List<Issue> issues = new ArrayList<Issue>();
    private final boolean json;
    private final Map<String, Integer> member = new HashMap<String, Integer>();
    private final String name;
    private final List<JSIdentifier> unused = new ArrayList<JSIdentifier>();
    private final List<String> urls = new ArrayList<String>();

    private JSLintResult(ResultBuilder b) {
        name = b.name;
        duration = b.duration;
        issues.addAll(b.issues);
        functions.addAll(b.functions);
        globals.addAll(b.globals);
        implieds.addAll(b.implieds);
        json = b.json;
        member.putAll(b.member);
        unused.addAll(b.unused);
        urls.addAll(b.urls);
    }

    /** How long did JSLint take to run? (milliseconds)*/
    public long getDuration() {
        return duration;
    }

    /** Return a list of functions defined. */
    public List<JSFunction> getFunctions() {
        return functions;
    }

    /** List all names defined in the global namespace. */
    public List<String> getGlobals() {
        return globals;
    }

    /** List all names with implied definitions. */
    public List<JSIdentifier> getImplieds() {
        return implieds;
    }

    /**
     * Return a list of all issues that JSLint found with this source code.
     */
    public List<Issue> getIssues() {
        return issues;
    }

    /** A count of how many times each member name is defined. */
    public Map<String, Integer> getMember() {
        return member;
    }

    /** The name of the source file just validated. */
    public String getName() {
        return name;
    }

    /** A list of unused names. */
    public List<JSIdentifier> getUnused() {
        return unused;
    }

    /** A list of URLs encountered (when parsing HTML). */
    public List<String> getUrls() {
        return urls;
    }

    /** Was this JSON? */
    public boolean isJson() {
        return json;
    }
}