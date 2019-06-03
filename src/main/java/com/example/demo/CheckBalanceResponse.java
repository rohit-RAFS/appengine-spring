package com.example.demo;

public class CheckBalanceResponse {
    Head head;
    Body body;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public CheckBalanceResponse(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    public class Head{
        String responseTimestamp;
        String version;
        String clientId;
        String signature;

        public String getResponseTimestamp() {
            return responseTimestamp;
        }

        public void setResponseTimestamp(String responseTimestamp) {
            this.responseTimestamp = responseTimestamp;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public Head(String responseTimestamp, String version, String clientId, String signature) {
            this.responseTimestamp = responseTimestamp;
            this.version = version;
            this.clientId = clientId;
            this.signature = signature;
        }
    }

    public class Body{
        Resultinfo resultinfo;

        public class Resultinfo{
            String resultStatus;
            String resultCode;
            String resultMsg;

            public Resultinfo(String resultStatus, String resultCode, String resultMsg) {
                this.resultStatus = resultStatus;
                this.resultCode = resultCode;
                this.resultMsg = resultMsg;
            }

            public String getResultStatus() {
                return resultStatus;
            }

            public void setResultStatus(String resultStatus) {
                this.resultStatus = resultStatus;
            }

            public String getResultCode() {
                return resultCode;
            }

            public void setResultCode(String resultCode) {
                this.resultCode = resultCode;
            }

            public String getResultMsg() {
                return resultMsg;
            }

            public void setResultMsg(String resultMsg) {
                this.resultMsg = resultMsg;
            }
        }

        String fundsSufficient;
        String addMoneyAllowed;
        String deficitAmount;

        public Body(Resultinfo resultinfo, String fundsSufficient, String addMoneyAllowed, String deficitAmount) {
            this.resultinfo = resultinfo;
            this.fundsSufficient = fundsSufficient;
            this.addMoneyAllowed = addMoneyAllowed;
            this.deficitAmount = deficitAmount;
        }

        public Resultinfo getResultinfo() {
            return resultinfo;
        }

        public void setResultinfo(Resultinfo resultinfo) {
            this.resultinfo = resultinfo;
        }

        public String getFundsSufficient() {
            return fundsSufficient;
        }

        public void setFundsSufficient(String fundsSufficient) {
            this.fundsSufficient = fundsSufficient;
        }

        public String getAddMoneyAllowed() {
            return addMoneyAllowed;
        }

        public void setAddMoneyAllowed(String addMoneyAllowed) {
            this.addMoneyAllowed = addMoneyAllowed;
        }

        public String getDeficitAmount() {
            return deficitAmount;
        }

        public void setDeficitAmount(String deficitAmount) {
            this.deficitAmount = deficitAmount;
        }
    }
}
