import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.filters.Filters;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.List;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/25 11:18
 */
public class ESUtilTest {

    static final Client client = new TransportClient()
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.225", 9300));

    public static void main(String[] args) {

        final EsUtil2 esUtil = new EsUtil2();
        final ActionRequestBuilder actionRequestBuilder = esUtil.convertSql("select nickname,userid from " +
                "es.cms_data_saibo$info where domainname = '百度' group by domainname", client);
        final SearchResponse response = (SearchResponse) actionRequestBuilder
                .execute().actionGet();

        /*System.out.println(response);
        final SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }*/

        //{"nickname":"大迈X5麦粉场","userid":"900b498c3985a936"}
        //Terms agg = response.getAggregations().get("terms");
        System.out.println(response);
        final List<Aggregation> aggregations = response.getAggregations().asList();
        System.out.println(getString(aggregations, ""));
        /*final List<Aggregation> aggregations = response.getAggregations().asList();
        for (Aggregation aggregation : aggregations) {
            //System.out.println("{"+aggregation.getName()+":" + b.getKey() + "     docCount:" + b.getDocCount());
            //Terms agg = response.getAggregations().get(aggregation.getName());
            Terms terms = (Terms) aggregation;
            for (Terms.Bucket b : terms.getBuckets()) {
                System.out.println("{" + aggregation.getName() + ":" + b.getKey() + "     docCount:" + b.getDocCount());
                final List<Aggregation> aggregations1 = b.getAggregations().asList();
                if (aggregations1 == null) {
                    continue;
                }
                for(Aggregation aggregation1 : aggregations1){
                    Terms classTerms = (Terms) aggregation1;
                    for (Terms.Bucket b2 : classTerms.getBuckets()) {
                        System.out.println("{" + aggregation1.getName() + ":" + b2.getKey() + "     docCount:" + b2.getDocCount());
                    }
                }
                System.out.println();
            }
        }*/
        /*final Map<String, Aggregation> stringAggregationMap = response.getAggregations().asMap();
        final Map<String, Aggregation> asMap = response.getAggregations().getAsMap();*/
        /*for (Terms.Bucket b : agg.getBuckets()) {
            System.out.println("{:" + b.getKey() + "     docCount:" + b.getDocCount());
            final Terms classTerms = b.getAggregations().get("terms");
            if (classTerms == null) {
                continue;
            }
            for (Terms.Bucket b2 : classTerms.getBuckets()) {
                System.out.println(b2.getKey() + ",,,,," + b2.getDocCount());
            }
        }*/


        /*String s = "{domainarea:北京,contenttype:微博,nickname:";
        String s1 = s.substring(0,s.lastIndexOf(","));
        System.out.println(s1);
        String s2 = s1.substring(0, s1.lastIndexOf(",")+1);
        System.out.println(s2);*/


    }

    public static String getString(List<Aggregation> aggregations, String string) {
        final Terms terms = (Terms) aggregations.get(0);
        if (terms.getName().equals("filter")) {
            Filters filters = (Filters) aggregations.get(0);
        }
        final List<Terms.Bucket> buckets = terms.getBuckets();
        if (buckets != null && buckets.get(0).getAggregations().asList().size() == 0) {
            if (string.contains("{")) {
                string += terms.getName() + ":";
            } else {
                string += "{" + terms.getName() + ":";
            }
            for (Terms.Bucket bucket : buckets) {
                final String string1 = string + bucket.getKey() + ",docCount:" + bucket.getDocCount() + "}";
                System.out.println(string1);
            }
            if (string.lastIndexOf(",") > -1) {
                final String s1 = string.substring(0, string.lastIndexOf(","));
                return s1.substring(0, s1.lastIndexOf(",") + 1);
            }
            //String s3 = s2.substring(0, s2.lastIndexOf(",")+1);
            return string;
        } else {
            assert buckets != null;
            for (Terms.Bucket bucket : buckets) {
                if (string.contains("{")) {
                    string += terms.getName() + ":";
                } else {
                    string += "{" + terms.getName() + ":";
                }
                string += bucket.getKey() + ",";
                string = getString(bucket.getAggregations().asList(), string);
            }
        }
        string = "";
        return string;
    }

}
