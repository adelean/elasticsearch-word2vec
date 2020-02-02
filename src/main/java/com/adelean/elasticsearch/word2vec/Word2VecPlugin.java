package com.adelean.elasticsearch.word2vec;

import com.adelean.elasticsearch.word2vec.model.ModelListAction;
import com.adelean.elasticsearch.word2vec.model.ModelLoader;
import com.adelean.elasticsearch.word2vec.model.ModelRestHandler;
import com.adelean.elasticsearch.word2vec.model.TransportModelListAction;
import com.adelean.elasticsearch.word2vec.upload.FinishUploadAction;
import com.adelean.elasticsearch.word2vec.upload.StartUploadAction;
import com.adelean.elasticsearch.word2vec.upload.StorePartAction;
import com.adelean.elasticsearch.word2vec.upload.TransportFinishUploadAction;
import com.adelean.elasticsearch.word2vec.upload.TransportStartUploadAction;
import com.adelean.elasticsearch.word2vec.upload.TransportStorePartAction;
import com.adelean.elasticsearch.word2vec.upload.UploadRestHandler;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.NodeEnvironment;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.watcher.ResourceWatcherService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.adelean.elasticsearch.word2vec.PrivilegedExecutor.executePrivileged;

@SuppressWarnings("unused")
public final class Word2VecPlugin extends Plugin implements AnalysisPlugin, ActionPlugin {
    static {
        setNoSubdirProperty();
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        return Collections.singletonMap("synonym_word2vec", SynonymWord2VecTokenFilterFactory::new);
    }

    @Override
    public List<RestHandler> getRestHandlers(
            Settings settings,
            RestController restController,
            ClusterSettings clusterSettings,
            IndexScopedSettings indexScopedSettings,
            SettingsFilter settingsFilter,
            IndexNameExpressionResolver indexNameExpressionResolver,
            Supplier<DiscoveryNodes> nodesInCluster) {
        UploadRestHandler uploadRestHandler = new UploadRestHandler(restController, settings);
        ModelRestHandler modelRestHandler = new ModelRestHandler(restController, settings);
        return Arrays.asList(uploadRestHandler, modelRestHandler);
    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {
        ActionHandler<StartUploadAction.StartUploadActionRequest, StartUploadAction.StartUploadActionResponse> startUploadHandler =
                new ActionHandler<>(StartUploadAction.INSTANCE, TransportStartUploadAction.class);
        ActionHandler<StorePartAction.StorePartActionRequest, StorePartAction.StorePartActionResponse> storePartHandler =
                new ActionHandler<>(StorePartAction.INSTANCE, TransportStorePartAction.class);
        ActionHandler<FinishUploadAction.FinishUploadActionRequest, FinishUploadAction.FinishUploadActionResponse> finishUploadHandler =
                new ActionHandler<>(FinishUploadAction.INSTANCE, TransportFinishUploadAction.class);

        ActionHandler<ModelListAction.ModelListActionRequest, ModelListAction.ModelListActionResponse> modelListHandler =
                new ActionHandler<>(ModelListAction.INSTANCE, TransportModelListAction.class);

        return Arrays.asList(startUploadHandler, storePartHandler, finishUploadHandler, modelListHandler);
    }

    @Override
    public Collection<Object> createComponents(
            Client client,
            ClusterService clusterService,
            ThreadPool threadPool,
            ResourceWatcherService resourceWatcherService,
            ScriptService scriptService,
            NamedXContentRegistry xContentRegistry,
            Environment environment,
            NodeEnvironment nodeEnvironment,
            NamedWriteableRegistry namedWriteableRegistry) {
        ModelLoader modelLoader = new ModelLoader(client);
        return Collections.singleton(modelLoader);
    }

    /**
     * Avoid UnsatisfiedLinkError provoked by renaming of .jar files.
     */
    private static void setNoSubdirProperty() {
        executePrivileged(() -> {
            System.setProperty("org.bytedeco.javacpp.cachedir.nosubdir", "true");
        });
    }
}
