package com.example.haaaa;



public class Connection {
    private GATE gate_a;
    private GATE gate_b;

    public Connection(GATE a, GATE b) {
        this.gate_a = a;
        this.gate_b = b;
    }

    public GATE[] get_connection() {
        return new GATE[]{gate_a, gate_b};
    }
} 