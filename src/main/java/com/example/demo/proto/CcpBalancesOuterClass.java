// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ccp_balances.proto

// Protobuf Java Version: 3.25.1
package com.example.demo.proto;

public final class CcpBalancesOuterClass {
  private CcpBalancesOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_example_proto_CcpBalances_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_example_proto_CcpBalances_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_example_proto_AccountIdentifier_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_example_proto_AccountIdentifier_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_example_proto_TradeDetails_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_example_proto_TradeDetails_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_example_proto_NestedTradeInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_example_proto_NestedTradeInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022ccp_balances.proto\022\021com.example.proto\"" +
      "\334\001\n\013CcpBalances\022?\n\021accountIdentifier\030\001 \001" +
      "(\0132$.com.example.proto.AccountIdentifier" +
      "\022\024\n\014businessDate\030\002 \001(\005\022\024\n\014exchangeName\030\003" +
      " \001(\t\0225\n\014tradeDetails\030\004 \001(\0132\037.com.example" +
      ".proto.TradeDetails\022)\n\006status\030\005 \001(\0162\031.co" +
      "m.example.proto.Status\"&\n\021AccountIdentif" +
      "ier\022\021\n\taccountid\030\001 \001(\t\"n\n\014TradeDetails\022\017" +
      "\n\007tradeId\030\001 \001(\t\022;\n\017nestedTradeInfo\030\002 \001(\013" +
      "2\".com.example.proto.NestedTradeInfo\022\020\n\010" +
      "tradeKey\030\003 \001(\t\"9\n\017NestedTradeInfo\022\024\n\014sec" +
      "urityCode\030\001 \001(\t\022\020\n\010quantity\030\002 \001(\001*9\n\006Sta" +
      "tus\022\013\n\007UNKNOWN\020\000\022\007\n\003NEW\020\001\022\r\n\tPROCESSED\020\002" +
      "\022\n\n\006FAILED\020\003B\032\n\026com.example.demo.protoP\001" +
      "b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_example_proto_CcpBalances_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_example_proto_CcpBalances_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_example_proto_CcpBalances_descriptor,
        new String[] { "AccountIdentifier", "BusinessDate", "ExchangeName", "TradeDetails", "Status", });
    internal_static_com_example_proto_AccountIdentifier_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_example_proto_AccountIdentifier_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_example_proto_AccountIdentifier_descriptor,
        new String[] { "Accountid", });
    internal_static_com_example_proto_TradeDetails_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_example_proto_TradeDetails_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_example_proto_TradeDetails_descriptor,
        new String[] { "TradeId", "NestedTradeInfo", "TradeKey", });
    internal_static_com_example_proto_NestedTradeInfo_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_example_proto_NestedTradeInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_example_proto_NestedTradeInfo_descriptor,
        new String[] { "SecurityCode", "Quantity", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
