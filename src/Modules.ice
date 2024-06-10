module Modules {

    class IntegralStructure {
        string functionS;
        double lRange;
        double uRange;
    }

    interface Client {
        void printResponse(string integral, string res);
    }

    interface Server {
        string developI(IntegralStructure integral);
        void printResponse(string res);
    }

    interface Broker {
        void attachServer(Server* server);
        void developI(Client* clientProxy, IntegralStructure integral);
    }

}