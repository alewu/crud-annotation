<#assign now=.now ? string("yyyy-MM-dd HH:mm:ss")>

<#if tableMetaDatas ?? >
[{
	<#list tableMetaDatas>
        <#items as tableMetaData> <#assign n = tableMetaData.customFields ? size />
	"baseInfo": {
		"apiName": "新增${tableMetaData.tableRemarks ? replace("表"," ")} ",
		"apiURI": "\/rent\/app\/${tableMetaData.entityName ? lower_case}s",
		"apiProtocol": 0,
		"apiSuccessMock": "",
		"apiFailureMock": "",
		"apiRequestType": 0,
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
	"requestInfo": [<#list tableMetaData.customFields>
	<#items as customField ><#if !(customField.columnName ? starts_with("gmt") || customField_index == 0)>{
		"paramNotNull": "1",
		"paramType": "0",
		"paramName": "${customField.remarks} ",
		"paramKey": "${customField.memberVariable}",
		"paramValue": "",
		"paramLimit": "",
		"paramNote": "",
		"paramValueList": [],
		"default": 0,
		"$index": ${customField_index}
	}</#if><#if customField_index lt (n-3) && customField_index gt 0>,</#if></#items></#list>],
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
	}
},{
	"baseInfo": {
		"apiName": "更新${tableMetaData.tableRemarks ? replace("表","")} ",
		"apiURI": "\/rent\/app\/${tableMetaData.entityName ? lower_case}s",
		"apiProtocol": 0,
		"apiSuccessMock": "",
		"apiFailureMock": "",
		"apiRequestType": 2,
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
	"requestInfo": [<#list tableMetaData.customFields>
	<#items as customField ><#if !(customField.columnName ? starts_with("gmt"))>{
		"paramNotNull": "1",
		"paramType": "0",
		"paramName": "${customField.remarks} ",
		"paramKey": "${customField.memberVariable}",
		"paramValue": "",
		"paramLimit": "",
		"paramNote": "",
		"paramValueList": [],
		"default": 0,
		"$index": ${customField_index}
	}</#if><#if customField_index lt (n-3)>,</#if></#items></#list>],
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
	}
},{
	"baseInfo": {
		"apiName": "删除${tableMetaData.tableRemarks ? replace("表"," ")} ",
		"apiURI": "\/rent\/app\/${tableMetaData.entityName ? lower_case}s",
		"apiProtocol": 0,
		"apiSuccessMock": "",
		"apiFailureMock": "",
		"apiRequestType": 3,
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
	"requestInfo": [<#if tableMetaData.customFields ??>{
		"paramNotNull": "1",
		"paramType": "0",
		"paramName": "${tableMetaData.customFields[0].remarks} ",
		"paramKey": "${tableMetaData.customFields[0].columnName}",
		"paramValue": "",
		"paramLimit": "",
		"paramNote": "",
		"paramValueList": [],
		"default": 0,
		"$index": 0
	}</#if>],
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
	}
},{
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