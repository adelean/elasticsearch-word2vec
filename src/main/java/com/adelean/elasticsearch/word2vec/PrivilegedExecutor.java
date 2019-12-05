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
    private static final PrivilegedExecutor INSTANCE = new PrivilegedExecutor();
    private static final SecurityManager SECURITY_MANAGER = System.getSecurityManager();

    private PrivilegedExecutor() {
    }

    public static PrivilegedExecutor getInstance() {
        return INSTANCE;
    }

    public void execute(Runnable command) {
        checkAccess();
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            command.run();
            return null;
        });
    }

    public <T> T execute(PrivilegedAction<T> action) {
        checkAccess();
        return AccessController.doPrivileged(action);
    }

    @SuppressWarnings("unchecked")
    public <T, EX extends Throwable> T executeWithException(PrivilegedExceptionAction<T> action) throws EX {
        checkAccess();
        try {
            return AccessController.doPrivileged(action);
        } catch (final PrivilegedActionException e) {
            throw (EX) e.getCause();
        }
    }

    private static void checkAccess() {
        if (SECURITY_MANAGER != null) {
            SECURITY_MANAGER.checkPermission(new SpecialPermission());
        }
    }
}
