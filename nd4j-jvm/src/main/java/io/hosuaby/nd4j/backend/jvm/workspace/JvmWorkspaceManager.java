package io.hosuaby.nd4j.backend.jvm.workspace;

import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.memory.provider.BasicWorkspaceManager;

public final class JvmWorkspaceManager extends BasicWorkspaceManager {

    @Override
    protected void pickReference(MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemoryWorkspace createNewWorkspace() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration, String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration, String id, Integer deviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemoryWorkspace getWorkspaceForCurrentThread(WorkspaceConfiguration configuration, String id) {
        throw new UnsupportedOperationException();
    }
}
