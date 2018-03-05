<#assign now=.now ? string("yyyy-MM-dd HH:mm:ss")>

<#if tableMetaDatas ?? >
[{
	<#list tableMetaDatas>
		<#items as tableMetaData> <#assign n = tableMetaData.customFields ? size />
	"baseInfo": {
		"apiName": "查询${tableMetaData.tableRemarks ? replace("表"," ")} ",
		"apiURI": "\/rent\/app\/${tableMetaData.entityName ? lower_case}s",
		"apiProtocol": 0,
		"apiSuccessMock": "",
		"apiFailureMock": "",
		"apiRequestType": 1,
		"apiStatus": 0,
		"starred": 0,
		"apiNoteType": 0,
		"apiNoteRaw": "",
		"apiNote": "",
		"apiRequestParamType": 0,
		"apiRequestRaw": "",
		"apiUpdateTime": "${now}"
	},
	"headerInfo": [],
	"requestInfo": [{
		"paramNotNull": "1",
		"paramType": "0",
		"paramName": "",
		"paramKey": "",
		"paramValue": "",
		"paramLimit": "",
		"paramNote": "",
		"paramValueList": [],
		"default": 0,
		"$index": 0
	}],
	"resultInfo": [],
	"mockInfo": {
		"rule": [{
			"paramKey": "",
			"paramType": "0",
			"$index": 0
		}],
		"result": "{}",
		"mockConfig": {
			"rule": "",
			"type": "object"
		}
	}<#sep>,
		</#items>
	</#list>
</#if>
}]