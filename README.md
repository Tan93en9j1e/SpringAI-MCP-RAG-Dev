# SpringAI-MCP-RAG-Dev
基于 SpringAI + MCP 协议 + RAG 构建的 AI 智能检索与工具调用一体化 Java 开发工程，完整实现 MCP 客户端/服务端双向通信、向量知识库检索、智能工具函数调用、对话记忆管理等能力。

## 项目简介
本项目纯 Java 开发（100% Java），采用多模块 Maven 工程拆分：
- `mcp-server`：MCP 服务端，提供自定义业务工具能力（商品信息 CRUD、数据查询等）
- `mcp-client`：MCP 客户端，集成 SpringAI 大模型对话、RAG 检索、对话记忆、邮箱查询工具
依托 MCP 标准协议实现 AI 大模型与本地业务工具的解耦调用，结合 RAG 向量检索实现私有知识库问答，适合企业内部智能助手、业务 AI 机器人二次开发。

## 仓库地址
```
https://github.com/Tan93en9j1e/SpringAI-MCP-RAG-Dev
```

## 项目模块说明
### 1. mcp-server（MCP服务端模块）
核心能力：
- 实现 MCP 标准服务协议，对外暴露业务工具接口
- 内置 `ProductTool` 商品工具：支持商品信息新增、查询、修改
- 提供标准化工具调用上下文、参数校验、返回结果封装
- 可扩展订单、用户、库存等自定义业务工具

提交迭代记录：
`feat(ProductTool): 添加商品信息修改功能`

### 2. mcp-client（MCP客户端模块）
核心能力：
- 基于 SpringAI 对接主流大模型，构建对话会话
- 集成对话记忆（Chat Memory），支持多轮上下文记忆
- 内置邮箱查询工具，可扩展文件读取、数据库查询等工具
- 对接 MCP Server，远程调用服务端业务工具
- 内置 RAG 检索链路，实现私有文档向量检索增强问答

提交迭代记录：
`feat(chat): 集成聊天记忆功能并新增邮箱查询工具`

## 技术栈
- 开发语言：Java
- 构建工具：Maven（`pom.xml` 统一父工程管理）
- AI 框架：Spring AI
- 通信协议：MCP（Model Context Protocol）
- 核心能力：RAG 检索增强生成、大模型多轮对话、自定义工具调用、会话记忆
- 工程规范：Git 版本管理，规范 feature 分支提交日志

## 项目结构
```
SpringAI-MCP-RAG-Dev
├── mcp-client/          # MCP客户端 + SpringAI对话 + RAG + 聊天记忆
├── mcp-server/          # MCP服务端 + 业务自定义工具(商品工具)
├── .gitignore           # Git忽略配置文件
└── pom.xml              # 父工程Maven配置，统一依赖管理
```

## 快速启动
### 1. 环境要求
- JDK 17+
- Maven 3.8+
- 大模型 API Key（OpenAI/通义千问/文心一言等，SpringAI 兼容）
- 向量库（支持 SpringAI 适配的向量存储：RedisVector、PGVector、Milvus 等）

### 2. 拉取代码
```bash
git clone https://github.com/Tan93en9j1e/SpringAI-MCP-RAG-Dev.git
cd SpringAI-MCP-RAG-Dev
```

### 3. 项目编译
```bash
mvn clean install -DskipTests
```

### 4. 启动顺序
1. 优先启动 `mcp-server` 模块，提供工具服务
2. 再启动 `mcp-client` 客户端，连接 MCP Server，加载大模型与 RAG 知识库
3. 访问对话接口，即可实现：知识库检索 + 远程业务工具调用 + 多轮记忆对话

## 核心功能特性
1. **MCP 客户端/服务端分离架构**
   工具服务与 AI 对话解耦，服务端可独立部署、多客户端复用业务工具。
2. **RAG 检索增强问答**
   加载私有文档向量化存储，大模型回答时优先检索内部资料，减少幻觉。
3. **多轮对话记忆**
   客户端持久化会话上下文，支持连续多轮对话，AI 记忆历史提问。
4. **可扩展自定义工具**
   - 服务端：商品管理工具，可扩展订单、用户、财务等业务工具
   - 客户端：内置邮箱查询工具，支持本地文件、数据库、API 工具扩展
5. **标准化 Git 迭代规范**
   提交日志统一使用 `feat/opt/fix(模块): 功能描述` 格式，便于版本迭代追溯。

## 扩展开发指南
### 新增服务端业务工具（mcp-server）
1. 在 `tool` 包下新建 XXTool 工具类
2. 实现 MCP 标准工具接口，定义入参、出参、工具描述
3. 注册到 MCP 服务暴露列表，重启 server 即可被客户端调用

### 新增客户端本地工具（mcp-client）
1. 新建工具类，添加 SpringAI 工具注解
2. 注入客户端上下文，注册至 AI 工具集合
3. 客户端会话自动识别并调用本地工具（如邮箱查询）

### RAG 知识库扩展
1. 配置向量存储连接信息
2. 编写文档加载器（PDF/Markdown/TXT）
3. 向量化入库后，对话链路自动触发检索增强

## 贡献者
- @Tan93en9j1e（项目唯一维护者）

## 后续规划
- 完善向量库配置示例，提供完整 RAG 演示 Demo
- 新增更多业务工具示例（订单、用户、文件解析）
- 增加 Web 前端对话页面，可视化展示问答与工具调用日志
- 完善异常捕获、日志打印、接口文档 Swagger
- 支持多模型动态切换、会话持久化存储（Redis）
