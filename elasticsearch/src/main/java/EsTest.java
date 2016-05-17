import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import sun.reflect.annotation.ExceptionProxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/21 13:51
 */
public class EsTest {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.sniff", true).put("cluster.name", "es").build();

        TransportClient client = new TransportClient(settings).addTransportAddresses(new InetSocketTransportAddress("192.168.1.212", 9300));

        SearchRequestBuilder srb1 = client
                .prepareSearch("cms_data_huishan")
                .setTypes("info")
                .setSize(5200);
                /*.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("createdate").gte("1443776524000"))
                        .must(QueryBuilders.rangeQuery("createdate").lte("1444640524000")));*/
        /*System.out.println(srb1);
        System.out.println(srb1.execute().actionGet());*/
        SearchResponse searchResponse = srb1.execute().actionGet();
        searchResponse.getHits().getHits();
        System.out.println();


        /*final Map map;
        try {
            map = client.admin().cluster()
                    .health(new ClusterHealthRequest("cms_data_*")).actionGet()
                    .getIndices();
            *//*IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest("cms_data_saibo*")).actionGet();
            indicesExistsResponse.isExists();*//*
            System.out.println(map);
        } catch (ElasticsearchException e1) {
            e1.printStackTrace();
        }*/

        /*XContentBuilder contentBuilder = null;
        try {
            contentBuilder = XContentFactory.jsonBuilder()
                    .startObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            contentBuilder.field("name", "hello");
            contentBuilder.field("indextime", String.valueOf(new Date().getTime()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        String json = null;
        try {
            json = contentBuilder.endObject().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.prepareIndex(indexName, indexName).setSource(json).execute()
                    .actionGet();
        } catch (ElasticsearchException e) {
            //init(host);
            e.printStackTrace();
        }
        /** indexName是否存在*/
        /*Map map = null;
        try {
            map = client.admin().cluster()
                    .health(new ClusterHealthRequest(indexName)).actionGet()
                    .getIndices();
        } catch (ElasticsearchException e1) {
            e1.printStackTrace();
        }
        //assert map != null;
        boolean exists = false;
        if (map != null) {
            exists = map.containsKey(indexName);
        }
        if (!exists) {
            client.admin().indices()
                    .create(new CreateIndexRequest(indexName)).actionGet();
            client.admin()
                    .cluster()
                    .health(new ClusterHealthRequest(indexName)
                            .waitForYellowStatus()).actionGet();

        }

        XContentBuilder builder = null;
        try {
            new XContentFactory();
            builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject(indexName)
                    .startObject("_source")
                    .field("enabled", false)
                    .endObject()
                    .startObject("properties");

            builder.startObject("name")
                    .field("type", "string")
                    .field("store", "no")
                    .field("index", "not_analyzed")
                    .field("indexAnalyzer", "ik");
            builder.endObject();

            *//*builder.startObject("indextime")
                    .field("type", "date")
                    .field("store", "no");
            builder.endObject();*//*

            builder.endObject()
                    .endObject()
                    .endObject();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final PutMappingRequest mapping = Requests.putMappingRequest(indexName).type(indexName).source(builder);
        try {
            client.admin().indices().putMapping(mapping).actionGet();
        } catch (ElasticsearchException e) {
            e.printStackTrace();
        }*/
        /*IndexResponse response = client.prepareIndex("cms_data_internetdb", "info","AU9O4WjTCdZR5WHm8raz")
                .setSource(jsonBuilder()
                                .startObject()
                                .field("myweb", "http://www.baidu.com")
                                .field("nickname", "test")
                                .field("indextime", new Date().getTime())
                                .endObject()
                )
                .execute()
                .actionGet();*/
        // isCreated() is true if the document is a new one, false if it has been updated
        //System.out.println("isCreated: " + response.isCreated());


        /*GetResponse response = client.prepareGet("cms_data_internetdb", "info","AU9O4WjTCdZR5WHm8raz")
                .execute()
                .actionGet();*/
        /*UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("cms_data_internetdb");
        updateRequest.type("info");
        updateRequest.id("53ee6d9d0990c234");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("nickname", "hahahahaha")
                .field("myweb", "hahahahaha")
                .field("nickname", "hahahahaha")
                .endObject());
        client.update(updateRequest).get();*/


        /*IndexRequest indexRequest = new IndexRequest("cms_data_internetdb", "info","AU9O4WjTCdZR5WHm8raz")
                .source(jsonBuilder()
                        .startObject()
                        .field("myweb", "http://www.baidu.com")
                        .field("nickname", "test")
                        .field("indextime", new Date().getTime())
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("cms_data_internetdb", "info","AU9O4WjTCdZR5WHm8raz")
                .doc(jsonBuilder()
                        .startObject()
                        .field("myweb", "http://www.sina.com")
                        .endObject())
                .upsert(indexRequest);
        client.update(updateRequest).get();*/


        /*DeleteResponse response = client.prepareDelete("cms_data_internetdb", "info","53ee6d9d0990c234")
                .execute()
                .actionGet();
        // Index name
        System.out.println("index: " + response.getIndex());
        // Type name
        System.out.println("type: " + response.getType());
        // Document ID (generated or not)
        System.out.println("id: " + response.getId());
        // Version (if it's the first time you index this document, you will get: 1)
        System.out.println("version: " + response.getVersion());*/



        /*BulkRequestBuilder bulkRequest = client.prepareBulk();

    // either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("cms_data_internetdb", "info")
                        .setSource(jsonBuilder()
                                        .startObject()
                                        .field("myweb", "http://www.sohou.com")
                                        .field("nickname", "test")
                                        .field("indextime", new Date().getTime())
                                        .endObject()
                        )
        );

        bulkRequest.add(client.prepareIndex("cms_data_internetdb", "info")
                        .setSource(jsonBuilder()
                                        .startObject()
                                        .field("myweb", "http://www.sina.com")
                                        .field("nickname", "test1")
                                        .field("indextime", new Date().getTime())
                                        .endObject()
                        )
        );

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        System.out.println(bulkResponse.hasFailures());*/



        /*BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {
                        System.out.println("before bulk");
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) {
                        System.out.println("after bulk: "+response.hasFailures());
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) {
                        System.out.println("after bulk error : "+failure.getLocalizedMessage());
                    }
                })
                .setBulkActions(5000)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .build();
        final Map<String,Object> source = new HashMap<>();
        source.put("myweb", "http://www.sina.com");
        source.put("nickname", "test1");
        source.put("indextime", new Date().getTime());
        bulkProcessor.add(new IndexRequest("cms_data_internetdb", "info").source(source));
        bulkProcessor.add(new DeleteRequest("cms_data_internetdb", "info", "AU9O9hqNCdZR5WHm8ra0"));
        bulkProcessor.close();*/

        /*SearchRequestBuilder searchRequestBuilder = client.prepareSearch("user_center1")
                .setTypes("user_center1")
                .setSearchType(SearchType.COUNT)
                .addAggregation(avg("domain_count")
                        .field("domain"));
        System.out.println(searchRequestBuilder);
        SearchResponse response = searchRequestBuilder
                .execute()
                .actionGet();
        System.out.println(response);
        SearchHits searchHits = response.getHits();*/


        /*QueryBuilder qb = termQuery("content", "夫妻");

        SearchResponse scrollResp = client.prepareSearch("cms_data_internetdb")
                .setSearchType(SearchType.SCAN)
                .setScroll(new TimeValue(60000))
                .setQuery(qb)
                .setSize(100).execute().actionGet();//100 hits per shard will be returned for each scroll
        //Scroll until no hits are returned
        while (true) {

            for (SearchHit hit : scrollResp.getHits().getHits()) {
                System.out.println(hit.getId());
            }
            System.out.println(scrollResp.getScrollId());
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
            //Break condition: No hits are returned
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }*/

        /*SearchRequestBuilder srb1 = client
                .prepareSearch().setQuery(QueryBuilders.queryStringQuery("夫妻")).setSize(1);
        SearchRequestBuilder srb2 = client
                .prepareSearch().setQuery(QueryBuilders.matchQuery("nickname", "test1")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .execute().actionGet();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println(hit.getId());
            }
        }
        System.out.println(nbHits);*/

        /*SearchResponse sr = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("agg1").field("field")
                )
                .addAggregation(
                        AggregationBuilders.dateHistogram("agg2")
                                .field("birth")
                                .interval(DateHistogram.Interval.YEAR)
                )
                .execute().actionGet();

        // Get your facet results
        Terms agg1 = sr.getAggregations().get("agg1");
        DateHistogram agg2 = sr.getAggregations().get("agg2");*/

        /*CountResponse response = client.prepareCount("cms_data_internetdb")
                .setQuery(termQuery("nickname", "test"))
                .execute()
                .actionGet();
        System.out.println(response.getCount());*/

        /*SearchResponse sr = client.prepareSearch("cms_data_internetdb");
        TermsFacet f = (TermsFacet) sr.getFacets().facetsAsMap().get("nickname");

        f.getTotalCount();      // Total terms doc count
        f.getOtherCount();      // Not shown terms doc count
        f.getMissingCount();    // Without term doc count

        // For each entry
        for (TermsFacet.Entry entry : f) {
            entry.getTerm();    // Term
            entry.getCount();   // Doc count
        }*/


    }
}
