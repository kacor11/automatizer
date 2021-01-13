# automatizer
Automatizer is Ansible-like application that allows you to manage remote hosts with REST api. It makes running simple tasks on multiple remote machines easy.

Application currently allows you to:
- Add/Remove/Store/Edit infomation about Remote Hosts.
- Execute Yaml Playbooks(Which can contain multiple shell commands to execute on group of selected hosts)
- Interact with user management system

#### Accepted yaml file format

```yaml
hostGroup: DEFAULT
tasks:
  - name: Print working directory on all hosts with group DEFAULT
    command: pwd
    

  - name: list files on all hosts with group DEFAULT
    command: ls
 

