{
    "query": {
        "bool": {
            "must": [
            <#if statisticsDate??>
                {
                    "term": {
                        "statisticsDate": {
                            "value": "${statisticsDate}",
                            "boost": 1.0
                        }
                    }
                },
                </#if>
                 <#if reportType??>
                {
                    "term": {
                        "reportType": {
                            "value": "${reportType}",
                            "boost": 1.0
                        }
                    }
                },
                </#if>
                <#if organId??>
                {
                    "term": {
                        "organId": {
                            "value": "${organId}",
                            "boost": 1.0
                        }
                    }
                },
                </#if>
                <#if exeType??>
                {
                    "term": {
                        "exeType": {
                            "value": "${exeType}",
                            "boost": 1.0
                        }
                    }
                },
                </#if>
                {
                    "term": {
                        "staffId": {
                            "value": "0",
                            "boost": 1.0
                        }
                    }
                }
            ],
            "adjust_pure_negative": true,
            "boost": 1.0
        }
    },
    "size": 0,
    "aggs": {
        "doneNumCount": {
            "sum": {
                "field": "doneNum"
            }
        },
        "motTotalNumCount": {
            "sum": {
                "field": "motTotalNum"
            }
        },
        "motNumCount": {
            "sum": {
                "field": "motNum"
            }
        },
        "undoNumCount": {
            "sum": {
                "field": "undoNum"
            }
        },
        "messageDealNumCount": {
            "sum": {
                "field": "messageDealNum"
            }
        },
        "phoneDealNumCount": {
            "sum": {
                "field": "phoneDealNum"
            }
        },
        "mailDealNumCount": {
            "sum": {
                "field": "mailDealNum"
            }
        },
        "talkDealNumCount": {
            "sum": {
                "field": "talkDealNum"
            }
        },
        "auspiciousDealNumCount": {
            "sum": {
                "field": "auspiciousDealNum"
            }
        },
        "tapeDealNumCount": {
            "sum": {
                "field": "tapeDealNum"
            }
        },
        "doneNumOtherCount": {
           <#if organId??>
                "sum": {
                "field": "doneNum"
            }
                <#else>
              "sum": {
                "field": "doneNum",
                "script": {
                    "source": "if (doc['statisticalFlag'].value == '0') { return doc['doneNum'].value; } else { return 0; }"
                }
            }
              </#if>
        },
        "otherDealNumCount": {
            "sum": {
                "field": "otherDealNum"
            }
        }
    }
}