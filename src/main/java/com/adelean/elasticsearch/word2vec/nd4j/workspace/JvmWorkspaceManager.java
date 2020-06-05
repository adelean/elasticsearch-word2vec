package com.adelean.elasticsearch.word2vec.nd4j.workspace;

import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.memory.provider.BasicWorkspaceManager;

public final class JvmWorkspaceManager extends BasicWorkspaceManager {

    @Override
    protected void pickReference(MemoryWorkspace workspace) {

    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration) {
        return null;
    }

    @Override
    public MemoryWorkspace createNewWorkspace() {
        return null;
    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration, String id) {
        return null;
    }

    @Override
    public MemoryWorkspace createNewWorkspace(WorkspaceConfiguration configuration, String id, Integer deviceId) {
        return null;
    }

    @Override
    public MemoryWorkspace getWorkspaceForCurrentThread(WorkspaceConfiguration configuration, String id) {
        return null;
    }
}
