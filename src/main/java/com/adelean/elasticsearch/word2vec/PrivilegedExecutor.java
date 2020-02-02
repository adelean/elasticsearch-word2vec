package com.adelean.elasticsearch.word2vec;

import org.elasticsearch.SpecialPermission;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * Executes actions with privileges configured in file {@code plugin-security.policy}.
 *
 * @author Alexei KLENIN
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/plugins/2.4/plugin-authors.html#_java_security_permissions">
 *     https://www.elastic.co/guide/en/elasticsearch/plugins/2.4/plugin-authors.html#_java_security_permissions</a>
 */
public final class PrivilegedExecutor {
    private PrivilegedExecutor() {
    }

    public static void executePrivileged(Runnable command) {
        checkAccess();
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            command.run();
            return null;
        });
    }

    public static <T> T executePrivileged(PrivilegedAction<T> action) {
        checkAccess();
        return AccessController.doPrivileged(action);
    }

    @SuppressWarnings("unchecked")
    public static <T, EX extends Throwable> T executePrivilegedWithException(PrivilegedExceptionAction<T> action) throws EX {
        checkAccess();
        try {
            return AccessController.doPrivileged(action);
        } catch (PrivilegedActionException e) {
            throw (EX) e.getCause();
        }
    }

    private static void checkAccess() {
        if (System.getSecurityManager() != null) {
            System.getSecurityManager().checkPermission(new SpecialPermission());
        }
    }
}
