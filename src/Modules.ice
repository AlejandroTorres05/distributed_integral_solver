module Modules {

    class IntegralStructure {
        string functionS;
        double lRange;
        double uRange;
    }

    interface Client {
        void printResponse(string integral, string res, string performance);
    }

    interface Server {
        void developI(int requestID, IntegralStructure integral);
        void printResponse(string res);
        double getLoad();
    }

    interface Broker {
        void attachServer(Server* server);
        void developI(Client* clientProxy, IntegralStructure integral);
        void join(int requestID, string res);
    }

}